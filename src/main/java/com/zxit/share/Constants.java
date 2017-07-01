package com.zxit.share;

/**
 * 功能描述：系统中使用的常量
 * 创建日期：2008-12-04
 */
public final class Constants {
    /*************************系统常量*******************************/
    /**
     * 全局变量：session用户变量
     */
    public static final String ApplicationSessionObject = "applicationSessionObject";
    /*************************GEO常量********************************/
    public static final double SEARCH_DIS = 0.5;//搜索范围单位：千米

    /*************************协议常量********************************/
    /**
     * 握手反馈消息
     */
    public static final String HANDLEGSS2WGIS = "9001";


    /*************************系统常量********************************/
    /**
     * 机构类型
     */
    public static final Integer ORGTYPE = 1;
    /**
     * 运行状态
     */
    public static final Integer STATUS = 2;
    /**
     * 图层类型
     */
    public static final Integer LAYER_TYPE = 3;
    /**
     * 终端类型
     */
    public static final Integer EMT_TYPE = 4;
    /**
     * 资源类型
     */
    public static final Integer RESOURCE = 11;
    public static final Integer monitor = 30;//监控
    public static final Integer poi = 50;//热点
    public static final Integer org = 70;//单位
    public static final Integer gps = 80;//GPS
    public static final Integer emt = 90;//设备

    /**
     * 禁用标记
     */
    public static final Integer ABD = 6;

    /**
     * 人员类型
     */
    //警察
    public static Integer police = 10;
    //清洁工
    public static Integer cleaners = 20;
}