package com.zxit.service;

import com.zxit.model.Aid;

/**
 * 接收GSS的HTTP信息
 *
 * @author nanxiaofeng
 */
public interface MsgReciveSwitchSerive {

    /**
     * 接收GSS的握手反馈信息
     *
     * @param msgId
     * @param aid
     */
    void switchHandleGss2Wgis(String msgId, Aid aid);

    /**
     * 接收GSS的定位信息
     *
     * @param msgId
     * @param aid
     */
    void switchPositionGss2Wgis(String msgId, Aid aid);


}
