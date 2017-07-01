package com.zxit.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import java.io.Serializable;
import java.sql.Clob;
import java.util.List;

/**
 * 一些说明：
 * 1，load和get
 * 性能上没有什么特殊的要求
 * 全部用get，load对于这种政府级项目只是吹毛求疵。
 * <p>
 * 2，flush小心点用，没有必要的话 ，别用就行了。
 * <p>
 * 3，带分页的SQL，不写了，还没JDBC好用。
 * <p>
 * 4，别没事儿非要操作游离对象，重新来一遍比找错误快得多
 * <p>
 * 5，
 */
public interface ABaseDao {
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
     * 同步数据库对象，执行 在事务提交之前
     */
    void flush();

    /**
     * 清理session缓存
     */
    void clear();

    /**
     * 通过String 创建一个lob
     */
    Clob createClob(String str);

    /**
     * 创造一个Criteria 实例
     */
    Criteria createCriteria(Class<?> type);


    /**
     * 从存储过程找到列表
     * @param type
     * @param key
     * @return
     */
//	  CachedRowSet findProcedures(Integer key);

    /**
     * 创建全文索引
     */
    void index();

    /**
     * 创建全文搜索集合
     *
     * @param search  搜索内容
     * @param content 字段Fileds的名字
     * @param type    类型
     * @return
     */
    <T> List<T> findByContext(String search, String content, Class<?> type);

}
