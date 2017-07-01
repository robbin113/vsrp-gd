package com.zxit.model;

/**
 * 旧系统
 * WGIS>>>GSS握手协议对象
 * [9000ZDXXDM:+站点信息代码+*#ZJM:+主机名+*#ZBY:+值班员+*#ZT:+状态+*#]
 *
 * @author nanxiaofeng
 */
public class HandleWgis2Gss {
    /**
     * 消息ID
     */
    private String msgId;
    /**
     * 站点信息代码
     */
    private String zdxxdm;
    /**
     * 主机名
     */
    private String zjm;
    /**
     * 值班员
     */
    private String zby;
    /**
     * 状态
     * 0-退出，1-登陆，2-空闲
     */
    private String zt;

    public HandleWgis2Gss() {

    }

    public HandleWgis2Gss(String msgId, String zdxxdm, String zjm, String zby, String zt) {
        this.msgId = msgId;
        this.zdxxdm = zdxxdm;
        this.zjm = zjm;
        this.zby = zby;
        this.zt = zt;
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

    public String getZjm() {
        return zjm;
    }

    public void setZjm(String zjm) {
        this.zjm = zjm;
    }

    public String getZby() {
        return zby;
    }

    public void setZby(String zby) {
        this.zby = zby;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }
}
