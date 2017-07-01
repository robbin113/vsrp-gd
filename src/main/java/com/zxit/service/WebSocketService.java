package com.zxit.service;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface WebSocketService extends WebSocketHandler {

    /**
     * 获取websocketSessionMap
     *
     * @return
     */
    Map<String, WebSocketSession> getUserSocketSessionMap();

    /**
     * 获取websocketSessionList
     */
    List<String> getUserSocketSessionList();

    /**
     * 单独发送
     *
     * @param zdxxdm
     * @param message
     * @throws IOException
     */
    void sendMessageToUser(String zdxxdm, TextMessage message) throws IOException;

    /**
     * 群体广播
     *
     * @param message
     * @throws IOException
     */
    void broadcast(TextMessage message) throws IOException;


}
