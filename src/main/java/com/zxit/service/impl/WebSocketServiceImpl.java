package com.zxit.service.impl;

import com.zxit.service.WebSocketService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Socket处理器
 *
 * @Date 2016年6月11日 下午1:19:50
 * @update 20170206 sendMessageToUser 添加线程同步
 * @reason 如果服务端断线重连之后会报错
 * @see ：afterConnectionEstablished
 * 执行消息句柄处理handleMessage
 * 发送、广播、错误处理sendMessageToUser、broadcast、handleTransportError
 * websocketsession关闭afterConnectionClosed
 * ex:
 * Object obj = JSON.parseObject(msg,Object.class).getMsgTo();
 * webSocketService.sendMessageToUser(obj.getMsgTo(), new TextMessage(msg));
 */
// 加上这一行可以保证前端访问时可以服务注入
@ServerEndpoint(value = "/ws/ws.do", configurator = SpringConfigurator.class)
@Service("webSocketService")
public class WebSocketServiceImpl extends TextWebSocketHandler implements WebSocketService {


    public static final Map<String, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<String, WebSocketSession>();
    }

    @Override
    public Map<String, WebSocketSession> getUserSocketSessionMap() {
        return userSocketSessionMap;
    }

    @Override
    public List<String> getUserSocketSessionList() {
        List<String> mapKeyList = new ArrayList<>(userSocketSessionMap.keySet());
        return mapKeyList;
    }


    /**
     * 建立连接后 接受到所有存在的会话用户上下文 并且存入websocekt的map集合
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        String zdxxdm = (String) session.getAttributes().get("zdxxdm");
        if (userSocketSessionMap.get(zdxxdm) == null) {
            userSocketSessionMap.put(zdxxdm, session);
        }
        System.out.println("websocket同步HTTPSession：" + userSocketSessionMap.toString());
    }

    /**
     * sendMessage方法封装在下面 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
     */
    @Override
    public void handleMessage(WebSocketSession session,
                              WebSocketMessage<?> message) throws Exception {
        System.out.println("网页通讯执行消息前置句柄！");
        if (message.getPayloadLength() == 0)
            return;
    }

    /**
     * 给某个用户发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessageToUser(String zdxxdm, TextMessage message)
            throws IOException {
        //打开特定用户消息传输通道
        WebSocketSession session = userSocketSessionMap.get(zdxxdm);
        if (session != null && session.isOpen()) {
            // 开始线程锁
            synchronized (session) {
                session.sendMessage(message);
            }
        } else {
            System.out.println("用户：" + zdxxdm + "websocketSession已失效！");
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     * @throws IOException
     */
    @Override
    public void broadcast(TextMessage message) throws IOException {
        Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
        // 多线程群发
        while (it.hasNext()) {
            final Entry<String, WebSocketSession> entry = it.next();
            if (entry.getValue().isOpen()) {
                //多线程广播
                new Thread(() -> {
                    try {
                        if (entry.getValue().isOpen()) {
                            //线程锁
                            synchronized (entry.getValue()) {
                                entry.getValue().sendMessage(message);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    /**
     * 消息传输错误处理
     */
    @Override
    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) {
        if (session.isOpen()) {
            try {
                session.close();
            } catch (IOException e) {
                System.out
                        .println("com.zxit.service.WebSocketService：handleTransportError关闭错误！");
            }
        }
        Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap
                .entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Entry<String, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }

    /**
     * 关闭连接后
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {
        Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Entry<String, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除用户ID=" + entry.getKey());
                System.out.println("Websocket:" + entry.getKey() + "已经关闭");
                break;
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
