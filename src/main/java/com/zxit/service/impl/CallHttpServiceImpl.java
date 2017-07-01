package com.zxit.service.impl;

import com.zxit.model.HandleWgis2Gss;
import com.zxit.model.PositionWgis2Gss;
import com.zxit.model.SystemConfig;
import com.zxit.service.CallHttpService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("callHttpService")
public class CallHttpServiceImpl implements CallHttpService {

    @Resource
    private SystemConfig systemConfig;


    // 日志
    private static Logger m_log = null;

    static {
        m_log = Logger.getLogger(CallHttpServiceImpl.class);
    }

    @Override
    public String sendURLContent(String urlStr) {
        System.out.println(">>>>>" + urlStr);
        String baseURL = systemConfig.getBaseUrl();
        urlStr = "socketString=" + urlStr;
        String result = null;
        try {
            result = sendURLByPost(baseURL, urlStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String sendURLByPost(String urlStr, String params) throws Exception {
        String result = "";
        try {
            URL url = new URL(urlStr);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/text"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/text"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            m_log.error(e.getMessage());
        }
        return result; // 自定义错误信息
    }

    @Override
    public String callBakPositionWgis2Gss(PositionWgis2Gss positionWgis2Gss) {
        String string = "";//反馈消息
        string = "["
                + positionWgis2Gss.getMsgId() + ""
                + "JD:" + positionWgis2Gss.getDwzbY() + "*%23"
                + "WD:" + positionWgis2Gss.getDwzbX() + "*%23"
                + "SSZRQ:" + positionWgis2Gss.getJjbh() + "*%23"
                + "]";
        return this.sendURLContent(string);
    }


    @Override
    public String sayLogin2GSS(HandleWgis2Gss handleWgis2Gss) {
        String string = "";//反馈消息
        string = "["
                + handleWgis2Gss.getMsgId() + ""
                + "ZDXXDM:" + handleWgis2Gss.getZdxxdm() + "*%23"
                + "ZJM:" + handleWgis2Gss.getZjm() + "*%23"
                + "ZBY:" + handleWgis2Gss.getZby() + "*%23"
                + "ZT:1*%23"
                + "]";
        return this.sendURLContent(string);
    }

    @Override
    public String sayHi2GSS(HandleWgis2Gss handleWgis2Gss) {
        String string = "";//反馈消息
        string = "["
                + handleWgis2Gss.getMsgId() + ""
                + "ZDXXDM:" + handleWgis2Gss.getZdxxdm() + "*%23"
                + "ZJM:" + handleWgis2Gss.getZjm() + "*%23"
                + "ZBY:" + handleWgis2Gss.getZby() + "*%23"
                + "ZT:2*%23"
                + "]";
        return this.sendURLContent(string);
    }


    @Override
    public String sayGoodBye2GSS(HandleWgis2Gss handleWgis2Gss) {
        String string = "";//反馈消息
        string = "["
                + handleWgis2Gss.getMsgId() + ""
                + "ZDXXDM:" + handleWgis2Gss.getZdxxdm() + "*%23"
                + "ZJM:" + handleWgis2Gss.getZjm() + "*%23"
                + "ZBY:" + handleWgis2Gss.getZby() + "*%23"
                + "ZT:0*%23"
                + "]";
        return this.sendURLContent(string);
    }




}
