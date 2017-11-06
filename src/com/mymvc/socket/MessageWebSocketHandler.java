package com.mymvc.socket;

import com.mymvc.constant.Constant;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by alan.luo on 2017/11/6.
 */
public class MessageWebSocketHandler extends TextWebSocketHandler {

    private final static Map<String,WebSocketSession> sessions = Collections.synchronizedMap(new HashMap<>());

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getAttributes().get(Constant.tokenKey).toString(),session);
        logger.info("connect to the webSocket success......");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("handleMessage"+message.toString());
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("handleTextMessage"+message.getPayload());
        super.handleTextMessage(session, message);
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        logger.info("handlePongMessage"+message.getPayload());
        super.handlePongMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }

        sessions.remove(session.getAttributes().get(Constant.tokenKey));
        logger.info("webSocket connection closed......");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("webSocket connection closed......");
        sessions.remove(session.getAttributes().get(Constant.tokenKey));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToUser(String token, TextMessage message) {
        if (sessions.containsKey(token) == false){
            return;
        }

        WebSocketSession user = sessions.get(token);
        try {
            if (user.isOpen()) {
                user.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToUsers(TextMessage message) {
        Set<String> keys = sessions.keySet();
        for (String token : keys) {
            try {
                if (sessions.containsKey(token)){
                    WebSocketSession user = sessions.get(token);
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
