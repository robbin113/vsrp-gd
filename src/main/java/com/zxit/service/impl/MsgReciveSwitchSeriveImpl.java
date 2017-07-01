package com.zxit.service.impl;

import com.alibaba.fastjson.JSON;
import com.zxit.model.Aid;
import com.zxit.model.HandleGss2Wgis;
import com.zxit.model.PositionGss2Wgis;
import com.zxit.service.MsgReciveSwitchSerive;
import com.zxit.service.WebSocketService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 消息接收路由器
 * 用于转发和处理GSS发送到WGIS服务器上的信息
 *
 * @author nanxiaofeng
 */
@Service("msgSwitchSerive")
public class MsgReciveSwitchSeriveImpl implements MsgReciveSwitchSerive {

    @Resource
    private WebSocketService webSocketService;

    @Override
    public void switchHandleGss2Wgis(String msgId, Aid aid) {
        HandleGss2Wgis handleGss2Wgis = JSON.parseObject(aid.getJsonString(), HandleGss2Wgis.class);
        handleGss2Wgis.setMsgId(msgId);
        String zdxxdm = handleGss2Wgis.getZdxxdm();
        List<String> userlist = webSocketService.getUserSocketSessionList();
        if (userlist.contains(zdxxdm)) {//websocketSession失效不执行
            try {
                webSocketService.sendMessageToUser(zdxxdm, new TextMessage(JSON.toJSONString(handleGss2Wgis)));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void switchPositionGss2Wgis(String msgId, Aid aid) {
        PositionGss2Wgis positionGss2Wgis = JSON.parseObject(aid.getJsonString(), PositionGss2Wgis.class);
        positionGss2Wgis.setMsgId(msgId);
        String zdxxdm = positionGss2Wgis.getZdxxdm();//站点信息
        List<String> userlist = webSocketService.getUserSocketSessionList();
        if (userlist.contains(zdxxdm)) {//websocketSession失效不执行
            try {
                String positonJSON = JSON.toJSONString(positionGss2Wgis);
                System.out.println(positonJSON);
                webSocketService.sendMessageToUser(zdxxdm, new TextMessage(JSON.toJSONString(positionGss2Wgis)));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
