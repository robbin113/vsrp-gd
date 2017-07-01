package com.zxit.action;

import com.zxit.model.ApplicationSessionObject;
import com.zxit.model.HandleWgis2Gss;
import com.zxit.model.SystemConfig;
import com.zxit.model.WgisIplist;
import com.zxit.service.CallHttpService;
import com.zxit.service.WgisIplistService;
import com.zxit.share.Constants;
import com.zxit.share.ServletParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
@RequestMapping("/login.do")
@SessionAttributes({"systemConfig", "applicationSessionObject"})
public class LoginController {

    @Resource
    private SystemConfig systemConfig;
    @Resource
    private WgisIplistService wgisIplistService;
    @Resource
    private CallHttpService callHttpService;
    /**
     * 登录方法
     *
     * @return
     */
    @RequestMapping(params = "method=login")
    public String login(ModelMap m, HttpServletRequest request) {
        //需要受理台给出
        String zdxxdm = ServletParameter.getParameter(request, "zdxxdm", "");
        String zby = ServletParameter.getParameter(request, "zby", "");

        System.out.println("加载系统常量配置");
        m.put("systemConfig", systemConfig);
        Constants constants = new Constants();
        m.put("constants", constants);
        System.out.println("验证IP……");
        /**
         * 验证IP注册
         */
        String zjm = this.getIpAddr(request);//工作机IP
        WgisIplist wgisIplist = wgisIplistService.findById(WgisIplist.class, zjm);
        if (wgisIplist == null) {
            m.put("err", "WGIS未注册！");
            return "login";
        }
        /**
         * 向GSS发送登录Http协议
         */
        String zt = "1";//登录状态  0退出，1登录，2空闲  我也是醉了  连个状态都不一样
        System.out.println("验证通过!" + zby + "正在进入WGIS！");
        HandleWgis2Gss handleWgis2Gss = new HandleWgis2Gss("9000", zdxxdm, zjm, zby, zt);
        //不管有没登录 先发退出协议
        String sayGoodBy2Gss = callHttpService.sayGoodBye2GSS(handleWgis2Gss);
        System.out.println("登出GSS业务服务器！" + sayGoodBy2Gss);
        String login2Gss = callHttpService.sayLogin2GSS(handleWgis2Gss);
        System.out.println("登录GSS业务服务器！" + login2Gss);
        /**
         * 受理台接入点
         */
        ApplicationSessionObject applicationSessionObject =
                new ApplicationSessionObject();
        applicationSessionObject.setZdxxdm(zdxxdm);
        applicationSessionObject.setZby(zby);
        m.put("applicationSessionObject", applicationSessionObject);
        return "/business/mapArcheType";
    }


    /**
     * 获得访问者IP
     *
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        System.out.println("访问者IP:" + ip);
        return ip;
    }


    /**
     * 登出并清除session
     * 从GSS登出
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "method=logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request) {
        //向GSS发出退出Http协议
        ApplicationSessionObject sessionObj = (ApplicationSessionObject) request.getSession().getAttribute(Constants.ApplicationSessionObject);
        //握手
        String msgId = "9000";
        HandleWgis2Gss handleWgis2Gss = new HandleWgis2Gss("9000", sessionObj.getZdxxdm(), sessionObj.getZjm(), sessionObj.getZby(), sessionObj.getZt());
        String login2Gss = callHttpService.sayGoodBye2GSS(handleWgis2Gss);
        System.out.println("退出GSS业务服务器！" + login2Gss);
        @SuppressWarnings("unchecked")
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        reMoveSessionAttribute(request);
        request.getSession().invalidate();
        return "login";
    }

    /**
     * 需要清除的session
     *
     * @param request
     */
    private void reMoveSessionAttribute(HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.ApplicationSessionObject);//上下文对象
    }


}
