package com.zxit.model;

/**
 * 定位消息
 *[5001DWZBX:+X坐标+*#DWZBY:+Y坐标+*#DWLB:+定位类别+*#BJDH:+报警电话+*#DZ:+地址+*#ZDXXDM:+站点信息代码+*#JJBH:+接警编号+*#]
 * @author nanxiaofeng
 */
public class PositionGss2Wgis {
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
     * 定位类别
     */
    private String dwlb;
    /**
     * 报警电话
     */
    private String bjdh;
    /**
     * 地址
     */
    private String dz;
    /**
     * 站点信息代码
     */
    private String zdxxdm;
    /**
     * 接警编号
     */
    private String jjbh;


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

    public String getDwlb() {
        return dwlb;
    }

    public void setDwlb(String dwlb) {
        this.dwlb = dwlb;
    }

    public String getBjdh() {
        return bjdh;
    }

    public void setBjdh(String bjdh) {
        this.bjdh = bjdh;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getZdxxdm() {
        return zdxxdm;
    }

    public void setZdxxdm(String zdxxdm) {
        this.zdxxdm = zdxxdm;
    }

    public String getJjbh() {
        return jjbh;
    }

    public void setJjbh(String jjbh) {
        this.jjbh = jjbh;
    }
}
