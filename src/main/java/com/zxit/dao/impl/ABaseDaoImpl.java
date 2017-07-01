package com.zxit.dao.impl;

import com.zxit.dao.ABaseDao;
import org.hibernate.*;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.Clob;
import java.util.List;

@Repository("aBaseDao")
public class ABaseDaoImpl implements ABaseDao {

    @Resource
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T findById(Class<T> type, Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(type, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findAll(Class<T> type) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(" from " + type.getSimpleName()).list();
    }

    @Override
    public Query findByHQL(String hql) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(hql);
    }


    @Override
    public int findTotalByHQL(String hql) {
        hql = "select count(1) " + hql;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        Number num = (Number) query.uniqueResult();
        return num.intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findWithPager(Class<T> type, String hql, int startNum,
                                     int pageTotal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setFirstResult(startNum);
        query.setMaxResults(pageTotal);
        return query.list();
    }

    @Override
    public SQLQuery findBySQL(String sql) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        return sqlQuery;
    }

    @Override
    public int findTotalBySQL(String sql) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select count(1) from (" + sql + ")");
        Number num = (Number) query.uniqueResult();
        return num.intValue();
    }

    @Override
    public void save(Object... entities) {
        int i = 1;
        for (Object entity : entities) {
            i++;
            Session session = sessionFactory.getCurrentSession();
            try {
                session.merge(entity);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(entity.getClass().toString());
            }
            if (i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }


    @Override
    public void merge(Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }

    @Override
    public void saveOrUpdate(Object entity) {
        Session session = sessionFactory.getCurrentSession();
        //session.saveOrUpdate(entity);
        session.merge(entity);
    }

    @Override
    public void updateObjects(Object... entities) {
        try {
            int i = 1;
            Session session = sessionFactory.getCurrentSession();
            for (Object entity : entities) {
                i++;
                session.update(entity);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }


    @Override
    public void refresh(Object... entities) {
        Session session = sessionFactory.getCurrentSession();
        session.refresh(entities);
    }

    @Override
    public void flush() {
        Session session = sessionFactory.getCurrentSession();
        session.flush();
    }

    @Override
    public void clear() {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
    }

    @Override
    public Clob createClob(String str) {
        Session session = sessionFactory.getCurrentSession();
        LobHelper lobHelper = session.getLobHelper();
        Clob clob = lobHelper.createClob(str);
        return clob;
    }

    @Override
    public void delete(Object... entities) {
        int i = 1;
        Session session = sessionFactory.getCurrentSession();
        for (Object entity : entities) {
            i++;
            if (entity != null) {
                session.delete(entity);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
        }
    }

    @Override
    public void deleteById(Class<?> type, Serializable id) {
        if (id == null) {
            return;
        }
        Object entity = findById(type, id);
        if (entity == null) {
            return;
        }
        delete(entity);
    }

    @Override
    public Criteria createCriteria(Class<?> type) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(type);
    }

    @Override
    public void index() {
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        try {
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("搜索引擎启动！");
    }

    @Override
    public <T> List<T> findByContext(String search, String content, Class<?> type) {
        this.index();
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(type).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().fuzzy().onField(content).matching(search).createQuery();
        Query query = fullTextSession.createFullTextQuery(luceneQuery, type).setMaxResults(1);
        List<T> list = query.list();
        return list;
    }

}
