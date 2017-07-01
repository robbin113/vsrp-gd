package com.zxit.tools;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilLog {
    private Logger m_log = Logger.getLogger("com.zxit.tools.UtilLog");
    int DbType = 0;
    private PrintWriter hlog;
    private String LogPath = "";

    public Logger getLogger() {
        return this.m_log;
    }

    public Logger getLog(String p_className) {
        return Logger.getLogger(p_className);
    }

    public UtilLog() {
        InitPath();
        try {
            this.hlog = new PrintWriter(new FileWriter(this.LogPath, true), true);
        } catch (Exception e) {
            this.m_log.warn("无法打开日志文件: " + this.LogPath + "... 异常描述:" + e.getMessage());
            this.hlog = new PrintWriter(System.out);
        }
    }

    public UtilLog(String p_classPath) {
        new UtilLog();
        this.m_log = Logger.getLogger(p_classPath);
    }

    private void InitPath() {
        String strCurDate = "run";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        strCurDate = formatter.format(currentTime);

        String l_path = URLDecoder.decode(getBaseFolder());

        File pathBak = new File(l_path + "logs");

        if (!pathBak.exists()) {
            pathBak.mkdir();
        }
        this.LogPath = (l_path + "logs/" + strCurDate + ".log");
    }

    private String getBaseFolder() {
        String strClassName = getClass().getName();
        String strPackageName = "";
        if (getClass().getPackage() != null) {
            strPackageName = getClass().getPackage().getName();
        }
        String strClassFileName = "";
        if (!"".equals(strPackageName))
            strClassFileName = strClassName.substring(strPackageName.length() + 1, strClassName.length());
        else {
            strClassFileName = strClassName;
        }
        URL url = null;
        url = getClass().getResource(strClassFileName + ".class");
        String strURL = url.toString();

        if (strURL.charAt(strURL.indexOf(':') + 1) != '/')
            strURL = strURL.substring(strURL.indexOf(':') + 1, strURL
                    .lastIndexOf('/'));
        else {
            strURL = strURL.substring(strURL.indexOf('/') + 1, strURL
                    .lastIndexOf('/'));
        }
        String TempStr = "/WEB-INF/";
        strURL = strURL.substring(0, strURL.indexOf(TempStr)) + "/";

        if (strURL.indexOf(":") >= 0) {
            return strURL;
        }
        return "/" + strURL;
    }

    public void LogToFile(String strContent) {
        try {
            this.hlog.println("************************************");
            this.hlog.println("方法:" + strContent);
        } catch (Exception e) {
            this.m_log.warn("向文件中保存日志出现异常:" + e.getMessage());
        }
    }

    public void LogToFile(String strContent, String str) {
        try {
            this.hlog.println("************************************");
            this.hlog.println("方法:" + strContent);
            this.hlog.println(str);
        } catch (Exception e) {
            this.m_log.warn("向文件中保存日志出现异常:" + e.getMessage());
        }
    }
}