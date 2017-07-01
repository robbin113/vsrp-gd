package com.zxit.service.impl;

import com.alibaba.fastjson.JSON;
import com.zxit.model.Aid;
import com.zxit.service.MsgReciveSwitchSerive;
import com.zxit.service.SocketMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("socketMsgService")
public class SocketMsgServiceImpl implements SocketMsgService {

    @Resource
    private MsgReciveSwitchSerive msgReciveSwitchSerive;

    @Override
    public Aid parseSocketMsg(String socketString) {
        Aid aid = new Aid();
        if (socketString.startsWith("[") && socketString.endsWith("]")) {
            //消息字符串 替换两端的中括号
            socketString = socketString.substring(1, socketString.lastIndexOf("]"));
            //消息字头
            String msgId = socketString.substring(0, 4);
            //消息体
            socketString = socketString.substring(4, socketString.length() - 2);
            Map<String, String> map = new HashMap<String, String>();
            String temp[] = socketString.split("\\*#");
            for (String s : temp) {
                if (s.indexOf(":") > 0) {//TODO 这步是多余的
                    String ctx[] = s.split(":", 2);//TODO 无论怎样只截取第一个冒号
                    int length = ctx.length;
                    if (length > 1) {
                        map.put(ctx[0], ctx[1]);
                    } else {
                        map.put(ctx[0], null);
                    }
                } else {
                    System.out.println("消息体解析缺失：");
                }
            }
            //Socket协议总载体
            aid.setMsgId(msgId);
            String jsonString = JSON.toJSONString(map);
            aid.setJsonString(jsonString);
            return aid;
        } else {
            System.out.println("消息体不正确！");
        }
        return null;
    }

    /**
     * 接收GSS业务服务器消息并向WGIS页面推送
     *
     * @return
     */
    @Override
    public void parseMsg2Entity(Aid aid) {
        String msgId = aid.getMsgId();//消息头
        switch (msgId) {
            //握手反馈
            case "9001":
                msgReciveSwitchSerive.switchHandleGss2Wgis(msgId, aid);
                break;
            //定位信息
            case "5000":
                msgReciveSwitchSerive.switchPositionGss2Wgis(msgId, aid);
                break;
            default:
                break;
        }

    }


}
