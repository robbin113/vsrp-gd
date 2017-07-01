package com.zxit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WgisIplist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WGIS_IPLIST")
public class WgisIplist implements java.io.Serializable {

    // Fields

    private String ip;
    private String th;
    private String tlx;

    // Constructors

    /**
     * default constructor
     */
    public WgisIplist() {
    }

    /**
     * full constructor
     */
    public WgisIplist(String ip) {
        this.ip = ip;
    }

    // Property accessors
    @Id
    @Column(name = "IP", unique = true, nullable = false, length = 15)
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "TH")
    public String getTh() {
        return th;
    }

    public void setTh(String th) {
        this.th = th;
    }

    @Column(name = "TLX")
    public String getTlx() {
        return tlx;
    }

    public void setTlx(String tlx) {
        this.tlx = tlx;
    }
}