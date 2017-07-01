package com.zxit.service;

import com.zxit.model.Aid;

/**
 * Socket>>>Object
 *
 * @author nanxiaofeng
 */
public interface SocketMsgService {

    /**
     * 110协议解析
     *
     * @param socketString
     * @return
     */
    Aid parseSocketMsg(String socketString);

    /**
     * 信息实例化
     *
     * @return
     */
    void parseMsg2Entity(Aid aid);


}
