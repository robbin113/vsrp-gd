package com.zxit.model;

public class SystemConfig {

    private String checker;// 系统注册码
    private String systemTitle;// 系统标题
    private String printTitle;// 打印标题
    private Integer debugMode;// 打开调试模式
    private Integer maxPerPage;// 每页显示最大数量
    private String filePath;
    private String fileType;
    private Integer mapType;
    private String mapUrl;
    private Integer mapOffline;
    private String mapApiUrl;
    private String baseUrl;

    // 无参构造
    public SystemConfig() {

    }

    //全参构造
    public SystemConfig(String checker, String systemTitle, String printTitle, Integer debugMode, Integer maxPerPage, String filePath, String fileType, Integer mapType, String mapUrl, Integer mapOffline, String mapApiUrl, String baseUrl) {
        this.checker = checker;
        this.systemTitle = systemTitle;
        this.printTitle = printTitle;
        this.debugMode = debugMode;
        this.maxPerPage = maxPerPage;
        this.filePath = filePath;
        this.fileType = fileType;
        this.mapType = mapType;
        this.mapUrl = mapUrl;
        this.mapOffline = mapOffline;
        this.mapApiUrl = mapApiUrl;
        this.baseUrl = baseUrl;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getSystemTitle() {
        return systemTitle;
    }

    public void setSystemTitle(String systemTitle) {
        this.systemTitle = systemTitle;
    }

    public String getPrintTitle() {
        return printTitle;
    }

    public void setPrintTitle(String printTitle) {
        this.printTitle = printTitle;
    }

    public Integer getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(Integer debugMode) {
        this.debugMode = debugMode;
    }

    public Integer getMaxPerPage() {
        return maxPerPage;
    }

    public void setMaxPerPage(Integer maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getMapType() {
        return mapType;
    }

    public void setMapType(Integer mapType) {
        this.mapType = mapType;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public Integer getMapOffline() {
        return mapOffline;
    }

    public void setMapOffline(Integer mapOffline) {
        this.mapOffline = mapOffline;
    }

    public String getMapApiUrl() {
        return mapApiUrl;
    }

    public void setMapApiUrl(String mapApiUrl) {
        this.mapApiUrl = mapApiUrl;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
