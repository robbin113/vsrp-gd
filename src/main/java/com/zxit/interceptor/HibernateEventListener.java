package com.zxit.interceptor;

import org.hibernate.SessionFactory;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 监听更新方法
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
//@Component
public class HibernateEventListener implements PostUpdateEventListener {
    // , PostInsertEventListener,PostDeleteEventListener

    @Resource
    private HttpServletRequest request;// 注入当前httprequest
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        String tablename = event.getEntity().getClass().getSimpleName();
        String emrId = event.getId().toString();
//		try {
//			if (!tablename.toUpperCase().equals("MisFiles".toUpperCase())
//					&& !tablename.toUpperCase().equals("SysLogs".toUpperCase())) {// 不监听文件表、日指表
//				for (int i = 0; i < event.getState().length; i++) {
//					// 更新前的值
//					Object oldValue = event.getOldState()[i];
//					// 更新后的新值
//					Object newValue = event.getState()[i];
//					// 更新的属性名
//					if (oldValue != null && newValue != null) {// 屏蔽空，要不会出错！
//						if (!oldValue.equals(newValue)) {// 发生改变的属性
//							String propertyName = event.getPersister()
//									.getPropertyNames()[i];
//							// 屏蔽干扰字段
//							if (!propertyName.equals("createTime")// 屏蔽创建时间
//									&& !propertyName.equals("lastModifyTime")// 屏蔽更新时间
//							) {
//								Session session = sessionFactory.openSession();
//								Transaction tx = session.beginTransaction();
//								O  emrModifyRecord = new MisEmrModifyRecord();
//								// Number pkey = (Number)
//								// session.createSQLQuery("select SEQ_MIS_EMR_MODIFY_ID.nextval  from dual").uniqueResult();
//								// emrModifyRecord.setId(pkey.longValue());
//								// 这个是序列
//								emrModifyRecord.setEmrId(emrId);
//								emrModifyRecord.setModifyTime(new Date());
//								emrModifyRecord.setModifyTable(tablename);
//								emrModifyRecord.setModifyContent(propertyName
//										.toString());
//								// TODO 判断如果是date类型的，检查前面的时间和后面的时间是否相同
//								// 因为springmvc传的是字符串，呵呵呵呵呵，这货监听早了
//								// 而且这货的类型还全部都是String我就日了狗了
//								// 所以时间先不记录了
//								if (!oldValue.getClass().getTypeName()
//										.equals("java.sql.Timestamp")
//										&& !newValue.getClass().getTypeName()
//												.equals("java.util.Date")) {
//									emrModifyRecord.setBefore(oldValue
//											.toString());
//									emrModifyRecord.setAfter(newValue
//											.toString());
//									SysMemberInfo sysMemberInfo = ((SysMemberInfo) request
//											.getSession().getAttribute(
//													Constants.ApplicationSessionObject));
//									emrModifyRecord
//											.setModifyUserid(sysMemberInfo
//													.getId());
//									session.save(emrModifyRecord);
//								}
//								session.flush();// 清空缓存后customer对象
//								tx.commit();
//								session.close();
//							}
//						}
//					}
//				}
//			}
//		} catch (SQLGrammarException e) {
//			System.out.println(e.getSQL());
//		}
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister arg0) {
        System.out.println("here...................");
        return false;
    }

    // @Override
    // public void onPostDelete(PostDeleteEvent arg0) {
    // System.out.println("delete...................");
    // }
    //
    // @Override
    // public void onPostInsert(PostInsertEvent arg0) {
    // System.out.println("insert...................");
    // }

}