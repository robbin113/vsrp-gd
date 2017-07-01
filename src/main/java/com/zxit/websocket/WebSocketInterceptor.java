package com.zxit.websocket;

import com.zxit.model.ApplicationSessionObject;
import com.zxit.share.Constants;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;


/**
 * websocket 执行拦截器
 * 注册机
 * Socket建立连接（握手）和断开
 * 握手后不在执行
 *
 * @Date 2015年6月11日 下午2:23:09
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * 可从httpRequest里找到需要的回话上下文
     * 注入到websocket的上下文
     */
    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) throws Exception {
        ApplicationSessionObject applicationSessionObject = new ApplicationSessionObject();
        applicationSessionObject =
                (ApplicationSessionObject)
                        ((ServletServerHttpRequest) request)
                                .getServletRequest().getSession(false).getAttribute(Constants.ApplicationSessionObject);
        System.out.println("Websocket:用户[ID:" + applicationSessionObject.toString() + "]已经建立连接");
        if (request instanceof ServletServerHttpRequest) {
            //设置WebSocketSession会话
            if (applicationSessionObject != null) {
                //TODO websocket接入点
                String zdxxdm = applicationSessionObject.getZdxxdm();
                attributes.put("zdxxdm", zdxxdm);
            }
        }

        return true;
    }

    /**
     * websocket Session创建后
     */
    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {
        System.out.println("wbesocket session被创建……");

    }

}
