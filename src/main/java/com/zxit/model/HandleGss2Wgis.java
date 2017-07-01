package com.zxit.model;

/**
 * 9001
 * GSS>>>WGIS握手协议反馈消息
 * [9001ZDXXDM:+站点信息代码+*#JG:+登陆结果+*#TIME:+同步时间+*#]
 * @author nanxiaofeng
 */
public class HandleGss2Wgis {
    /**
     * 消息ID
     */
    private String msgId;
    /**
     * 站点信息代码
     */
    private String zdxxdm;
    /**
     * 登陆结果
     */
    private String jg;
    /**
     * time
     */
    private String time;

    public HandleGss2Wgis() {
    }

    public HandleGss2Wgis(String msgId, String zdxxdm, String jg, String time) {
        this.msgId = msgId;
        this.zdxxdm = zdxxdm;
        this.jg = jg;
        this.time = time;
    }

    public String getMsgId() {

        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getZdxxdm() {
        return zdxxdm;
    }

    public void setZdxxdm(String zdxxdm) {
        this.zdxxdm = zdxxdm;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
