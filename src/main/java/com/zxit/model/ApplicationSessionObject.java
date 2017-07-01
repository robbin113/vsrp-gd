package com.zxit.model;

/**
 * session上下文 用于存放webgis运行过程中放置的各种参数
 *
 * @author nanxiaofeng
 */
public class ApplicationSessionObject {

    private String zdxxdm;
    private String dhhm;//座机号码
    private String zby;//值班员
    private String dwbm;//单位编码
    private String dwmc;//单位名称
    private String zjm;//主机名
    private String zt;//状态
    private String xtsf;//系统身份

    public ApplicationSessionObject() {
    }

    public ApplicationSessionObject(String zdxxdm, String dhhm, String zby, String dwbm, String dwmc, String zjm, String zt, String xtsf) {
        this.zdxxdm = zdxxdm;
        this.dhhm = dhhm;
        this.zby = zby;
        this.dwbm = dwbm;
        this.dwmc = dwmc;
        this.zjm = zjm;
        this.zt = zt;
        this.xtsf = xtsf;
    }

    public String getZdxxdm() {
        return zdxxdm;
    }

    public void setZdxxdm(String zdxxdm) {
        this.zdxxdm = zdxxdm;
    }

    public String getDhhm() {
        return dhhm;
    }

    public void setDhhm(String dhhm) {
        this.dhhm = dhhm;
    }

    public String getZby() {
        return zby;
    }

    public void setZby(String zby) {
        this.zby = zby;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getZjm() {
        return zjm;
    }

    public void setZjm(String zjm) {
        this.zjm = zjm;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getXtsf() {
        return xtsf;
    }

    public void setXtsf(String xtsf) {
        this.xtsf = xtsf;
    }
}
