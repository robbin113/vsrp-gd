package com.zxit.model;

/**
 * Socket协议总载体
 * 通过解析msgId进行消息对象分发
 *
 * @author nanxiaofeng
 */
public class Aid {

    private String msgId;


    private String jsonString;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }


}
