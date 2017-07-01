package com.zxit.interceptor;


import org.apache.log4j.Logger;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate4.HibernateJdbcException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 全局拦截器
 *
 * @author Administrator
 */
public class MVCInterceptor extends SimpleMappingExceptionResolver implements HandlerInterceptor {

    //	@Resource
//	private SysLogsService sysLogsService;
//	@Resource
//	private MisEmrTemplatePageService misEmrTemplatePageService;
//	@Resource
//	private MisEmrActivityService misEmrActivityService;
//	@Resource
//	private SysMemberInfoService sysMemberInfoService;
//	
//	
    private long timePoint;//方法开始执行时间

    private long timePoint2;//方法结束执行时间
    //
//	
    //记录日志
    Logger m_log = Logger.getLogger("com.zxit.interceptor.MVCInterceptor");

    /**
     * 执行前
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //方法开始时间
        timePoint = new Date().getTime();
        return true;
    }

    /**
     * 执行后
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Date now = new Date();

        String handlerStr = handler.toString()
                .replaceAll(",", "")
                .replace("(", "").replace(")", "")
                .replace("com.zxit.action", "")
                .replace("javax.servlet.http.HttpServletRequest", "")
                .replace("public", "")
                .replace("java.lang.", "")
                .replace("String", "")
                .replace("Integer", "");

        /**
         * 行为日志
         */
        //this.saveActivity(request,handlerStr,ex,now);
        /**
         * 时间过长
         */
        //方法结束时间
        timePoint2 = now.getTime();
        long timing = timePoint2 - timePoint;//是检查
        if (timing >= 5000) {
            //log2DB("timing2Long",handler.toString(),"IS NOT ERR","方法执行时间过长"+timing);
        }
        /**
         *全部错误内容
         */
        String exStr = "EMR ERROR<br>";
        //拦截器异常抛出不捕获，不影响系统使用这里的异常会被捕获到log文件。
        if (ex != null) {
            String exName = ex.getClass().getName();
            //各种错误
            if (exName.equals("org.hibernate.exception.SQLGrammarException")) {
                SQLGrammarException ex_sql = (SQLGrammarException) ex;
                exStr = ex_sql.getSQL();
            } else if (exName.equals("org.springframework.orm.hibernate4.HibernateJdbcException")) {
                HibernateJdbcException ex_hib = (HibernateJdbcException) ex;
                exStr = ex_hib.getSQLException().getMessage();
            } else if (exName.equals("org.springframework.dao.DataIntegrityViolationException")) {
                DataIntegrityViolationException ex_spring = (DataIntegrityViolationException) ex;
                exStr = ex_spring.getRootCause().getMessage();
            } else {
                exStr += "错误方法：" + handlerStr + "<br/>错误位置：" + ex.toString();
            }

            request.setAttribute("exStr", exStr);

            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(exStr);
            m_log.error("----------------------------------------------");
            m_log.error("错误信息：" + exStr.replace("com.zxit.interceptor.MVCInterceptor.afterCompletion", "").replace("com.zxit.interceptor.MVCInterceptor", ""));
            m_log.error("\r\n");
            m_log.error("错误位置：" + handler.toString());
            m_log.error("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
            m_log.error("\r\n");
            //记录日志han
            log2DB("excute", handler.toString().replace("(", "<br/>").replace(")", "") + "<br/>", ex.toString(), exStr);
        }

    }

    private void saveActivity(HttpServletRequest request, String handlerStr,
                              Exception ex, Date d) {
//		MisEmrActivity misEmrActivity = new MisEmrActivity();
//		SysMemberInfo sysMemberInfo = (SysMemberInfo)request.getSession().getAttribute(Constants.ApplicationSessionObject);
//		if(sysMemberInfo!=null){
//			misEmrActivity.setMemberId(sysMemberInfo.getId());
//			String orgId = ""; 
//			orgId = sysMemberInfo.getSysOrgInfo().getOrgId();
//			misEmrActivity.setOrgId(orgId);
//		}
//		misEmrActivity.setAction(handlerStr);
//		misEmrActivity.setActiontime(d);
//		if(ex==null){
//			misEmrActivity.setSuccess(1);
//		}
        //misEmrActivityService.saveOrUpdate(misEmrActivity);

    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }


    /**
     * @param type        类型
     * @param message     目标
     * @param longmsg_str 错误信息字符串
     * @param note        备注
     */
    public void log2DB(String type, String message, String longmsg, String note) {
//		SysLogs sysLogs = new SysLogs(null, new Date(), note, type);
//		sysLogs.setMessage(message);
//		sysLogs.setLogdate(new Date());
//		sysLogsService.saveSysCode(sysLogs);
    }


}
