package com.zxit.websocket;


import com.zxit.service.WebSocketService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * WebScoket配置处理器
 *
 * @author nanxiaofeng
 * @Date 2016年6月11日 下午1:15:09
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Resource
    private WebSocketService webSocketService;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketService, "/wk/ws.do").addInterceptors(new WebSocketInterceptor());
        //sockjs
        //registry.addHandler(webSocketService, "/wk/sockjs/ws.do").addInterceptors(new WebSocketInterceptor()).withSockJS();
    }


}
