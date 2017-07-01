package com.zxit.action;


import com.zxit.model.Aid;
import com.zxit.service.SocketMsgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 接收GSS+HTTP服务消息
 *
 * @author nanxiaofeng
 */
@Controller
@RequestMapping("/socketMsg.do")
public class SocketMsgController {

    @Resource
    private SocketMsgService socketMsgService;

    /**
     * 消息接收器
     * 用于接收外部http消息
     *
     * @param socketString
     */
    @RequestMapping
    public void recSocket(String socketString) {
        if (socketString == null || socketString.equals("")) return;
        System.out.println("<<<<<：" + socketString);
        Aid aid = socketMsgService.parseSocketMsg(socketString);
        socketMsgService.parseMsg2Entity(aid);
    }

}
