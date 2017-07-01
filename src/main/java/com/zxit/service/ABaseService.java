package com.zxit.service;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import java.io.Serializable;
import java.util.List;

public interface ABaseService {
    /**
     * 通过序列名生成主键
     *
     * @param sqName
     * @return
     */
    Long findPK(String sqName);

    /**
     * 通过id查找对象
     */
    <T> T findById(Class<T> type, Serializable id);

    /**
     * 通过对象查出所有列表
     */
    <T> List<T> findAll(Class<T> type);

    /**
     * 通过一个hql得到Query
     */
    Query findByHQL(String hql);

    /**
     * 获得信息个数HQL
     */
    int findTotalByHQL(String hql);

    /**
     * 带分页查询
     */
    <T> List<T> findWithPager(Class<T> type, String hql, int startNum, int pageTotal);

    /**
     * 通过一个sql得到一个Query对象
     */
    SQLQuery findBySQL(String sql);

    /**
     * 获得信息个数SQL
     */
    int findTotalBySQL(String sql);
    /**
     * 带分页的Sql查询
     */
    // <T> List<T> findWithPagerSQL(String sql, int startNum,int pageTotal);

    /**
     * 保存，你懂的
     */
    void save(Object... entities);

    /**
     * 更新，你也懂得
     */
    void updateObjects(Object... entities);

    void update(Object entity);

    /**
     * 持久化
     */
    void merge(Object entity);

    /**
     * 更新或保存
     */
    void saveOrUpdate(Object entity);

    /**
     * 删除，不多说
     */
    void delete(Object... entities);

    /**
     * 主键删除
     */
    void deleteById(Class<?> type, Serializable id);

    /**
     * 刷新数据库对象
     *
     * @param entities
     */
    void refresh(Object... entities);

    /**
     * 创建全文搜索集合
     */
    <T> List<T> findByContext(String search, String content, Class<?> type);

}