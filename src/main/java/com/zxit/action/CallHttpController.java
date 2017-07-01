package com.zxit.action;


import com.zxit.model.ApplicationSessionObject;
import com.zxit.model.HandleWgis2Gss;
import com.zxit.service.CallHttpService;
import com.zxit.share.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 */
@Controller
@RequestMapping("/callHttp.do")
public class CallHttpController {

    @Resource
    private CallHttpService callHttpService;

    /**
     * 获得session中的常量
     *
     * @param request
     * @return
     */
    private ApplicationSessionObject getApplicationSessionObjectFromHttpSession(HttpServletRequest request) {
        ApplicationSessionObject sessionObj =
                (ApplicationSessionObject) request.getSession().getAttribute(Constants.ApplicationSessionObject);
        return sessionObj;
    }

    /**
     * 前端Ajax
     * 9000握手协议控制器
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(params = "method=sayHi2GSS")
    public String sayHi2GSS(HttpServletRequest request) {
        //存在参数
        ApplicationSessionObject sessionObj = this.getApplicationSessionObjectFromHttpSession(request);
        if (sessionObj == null) {
            return "{\"err\":\"应用上下文为空！\"}";
        }
        String msgId = "9000";
        //握手 TODO 这里没有必要那么麻烦： dwbh+tlx+th即可完成握手，为了迎合系统的协议，只有这样咯
        HandleWgis2Gss handleWgis2Gss = new HandleWgis2Gss(
                msgId, sessionObj.getZdxxdm(), sessionObj.getZjm(), sessionObj.getZby(),sessionObj.getZt());
        //向HTTP服务器发送握手消息，并告诉服务器客户端的状态
        String resutltString = callHttpService.sayHi2GSS(handleWgis2Gss);
        System.out.println("握手反馈:" + resutltString);
        return resutltString;
    }

    /**
     * 前端Ajax
     * 5013定位反馈
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(params = "method=PositionWgis2Gss")
    public String PositionWgis2Gss(HttpServletRequest request, com.zxit.model.PositionWgis2Gss positionWgis2Gss) {
        ApplicationSessionObject sessionObj = this.getApplicationSessionObjectFromHttpSession(request);
        if (sessionObj == null) {
            return "{\"err\":\"应用上下文为空！\"}";
        }
        String msgId = "5013";
        positionWgis2Gss.setMsgId(msgId);
        //向HTTP服务器发送握手消息，并告诉服务器客户端的状态
        String resutltString = callHttpService.callBakPositionWgis2Gss(positionWgis2Gss);
        System.out.println("定位反馈:" + resutltString);
        return resutltString;
    }


    /**
     * 前端Ajax
     * 9000握手协议控制器
     * 登出GSS
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(params = "method=sayGoodBye2GSS")
    public String sayGoodBye2GSS(HttpServletRequest request) {
        System.out.println("用户登出GSS业务服务器");
        //存在参数
        ApplicationSessionObject sessionObj = this.getApplicationSessionObjectFromHttpSession(request);
        if (sessionObj == null) {
            return null;
        }
        String msgId = "9000";
        //握手 TODO 这里没有必要那么麻烦： dwbh+tlx+th即可完成握手，为了迎合系统的协议，只有这样咯
        HandleWgis2Gss handleWgis2Gss = new HandleWgis2Gss(
                msgId, sessionObj.getZdxxdm(), sessionObj.getZjm(), sessionObj.getZby(),sessionObj.getZt());
        //向HTTP服务器发送握手消息，并告诉服务器客户端的状态
        callHttpService.sayGoodBye2GSS(handleWgis2Gss);
        return null;
    }


}
