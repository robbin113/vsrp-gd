package com.zxit.model;

/**
 * 5013
 * [5013DWZBX:+X坐标+*#DWZBY:+Y坐标+*#JJBH:+接警编号+*#]
 * webGis向GIS业务服务器转发定位反馈
 * @author nanxiaofeng
 */
public class PositionWgis2Gss {
    /**
     * 消息ID
     */
    private String msgId;
    /**
     * 定位坐标X
     */
    private String dwzbX;
    /**
     * 定位坐标Y
     */
    private String dwzbY;
    /**
     * 接警流水号
     */
    private String jjbh;

    // smiple
    public PositionWgis2Gss() {

    }


    public PositionWgis2Gss(String msgId, String dwzbX, String dwzbY, String jjbh) {
        this.msgId = msgId;
        this.dwzbX = dwzbX;
        this.dwzbY = dwzbY;
        this.jjbh = jjbh;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getDwzbX() {
        return dwzbX;
    }

    public void setDwzbX(String dwzbX) {
        this.dwzbX = dwzbX;
    }

    public String getDwzbY() {
        return dwzbY;
    }

    public void setDwzbY(String dwzbY) {
        this.dwzbY = dwzbY;
    }

    public String getJjbh() {
        return jjbh;
    }

    public void setJjbh(String jjbh) {
        this.jjbh = jjbh;
    }
}
