package com.ainijar.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    //使用道格李的ConcurrentHashSet, 放的是WebSocketServer而不是session为了复用自己方法
    private static transient volatile Set<WebSocketServer> webSocketSet = ConcurrentHashMap.newKeySet();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        sendMessage("连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端(" + session.getId() + ")的消息:" + message);
        sendMessage("Hello, nice to hear you! There are " + webSocketSet.size() + " users like you in total here!");
    }

    /**
     * Title: onError
     * Description: 发生错误时候回调函数
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("webSocket发生错误:" + error.getClass() + error.getMessage());
    }

    /**
     * Title: sendMessage
     * Description: 向客户端发送消息
     * @param message
     * @throws IOException
     */
    public boolean sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
            return true;
        } catch (IOException error) {
            log.error("webSocket-sendMessage发生错误:" + error.getClass() + error.getMessage());
            return false;
        }
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) {
        log.info("webSocket-sendInfo群发消息：" + message);
        for (WebSocketServer item : webSocketSet) {
            item.sendMessage(message);
        }
    }

    /**
     * Title: getOnlineCount
     * Description: 获取连接数
     * @return
     */
    public static int getOnlineCount() {
        return webSocketSet.size();
    }

}
