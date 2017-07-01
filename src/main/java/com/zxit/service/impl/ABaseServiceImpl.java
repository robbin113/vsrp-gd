package com.zxit.service.impl;

import com.zxit.dao.ABaseDao;
import com.zxit.service.ABaseService;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service("aBaseService")
public class ABaseServiceImpl implements ABaseService {

    @Resource
    private ABaseDao aBaseDao;

    @Override
    public Long findPK(String sqName) {
        String sql = "select " + sqName + ".nextval  from dual";
        return Long.valueOf(aBaseDao.findBySQL(sql).uniqueResult().toString());
    }


    @Override
    public <T> T findById(Class<T> type, Serializable id) {
        return aBaseDao.findById(type, id);
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
        return aBaseDao.findAll(type);
    }

    @Override
    public Query findByHQL(String hql) {
        return aBaseDao.findByHQL(hql);
    }

    @Override
    public int findTotalByHQL(String hql) {
        return aBaseDao.findTotalByHQL(hql);
    }

    @Override
    public <T> List<T> findWithPager(Class<T> type, String hql, int startNum,
                                     int pageTotal) {
        return aBaseDao.findWithPager(type, hql, startNum, pageTotal);
    }

    @Override
    public SQLQuery findBySQL(String sql) {
        return aBaseDao.findBySQL(sql);
    }

    @Override
    public int findTotalBySQL(String sql) {
        return aBaseDao.findTotalBySQL(sql);
    }

    @Override
    public void save(Object... entities) {
        aBaseDao.save(entities);
    }

    @Override
    public void updateObjects(Object... entities) {
        aBaseDao.updateObjects(entities);

    }

    @Override
    public void update(Object entity) {
        aBaseDao.update(entity);
    }

    @Override
    public void merge(Object entity) {
        aBaseDao.merge(entity);
    }

    @Override
    public void saveOrUpdate(Object entity) {
        aBaseDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Object... entities) {
        aBaseDao.delete(entities);
    }

    @Override
    public void deleteById(Class<?> type, Serializable id) {
        aBaseDao.deleteById(type, id);
    }

    @Override
    public void refresh(Object... entities) {
        aBaseDao.refresh(entities);
    }

    @Override
    public <T> List<T> findByContext(String search, String content, Class<?> type) {
        return aBaseDao.findByContext(search, content, type);
    }

}
