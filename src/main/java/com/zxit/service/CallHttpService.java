package com.zxit.service;

import com.zxit.model.HandleWgis2Gss;
import com.zxit.model.PositionWgis2Gss;

/**
 * GSS通讯服务
 * 访问外部HTTP服务
 *
 * @author nanxiaofeng
 */
public interface CallHttpService {

    /**
     * 程序访问Http接口
     *
     * @param urlStr
     * @return
     */
    String sendURLContent(String urlStr);

    /**
     * post方式请求http服务
     *
     * @param urlStr
     * @param params name=yxd&age=25
     * @return
     * @throws Exception
     */
    String sendURLByPost(String urlStr, String params) throws Exception;


    /**
     * 登录协议
     *
     * @param handleWgis2Gss
     * @return
     */
    String sayLogin2GSS(HandleWgis2Gss handleWgis2Gss);

    /**
     * 上线握手协议
     *
     * @param handleWgis2Gss
     */
    String sayHi2GSS(HandleWgis2Gss handleWgis2Gss);

    /**
     * 退出协议
     *
     * @param handleWgis2Gss
     */
    String sayGoodBye2GSS(HandleWgis2Gss handleWgis2Gss);

    /**
     * 定位反馈信息
     * [5013DWZBX:+X坐标+*#DWZBY:+Y坐标+*#JJBH:+接警编号+*#]
     * @param positionWgis2Gss
     */
    String callBakPositionWgis2Gss(PositionWgis2Gss positionWgis2Gss);


}
