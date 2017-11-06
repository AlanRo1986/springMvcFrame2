package com.mymvc.system.bus;

import com.mymvc.socket.MessageWebSocketHandler;
import com.mymvc.system.annotation.EventBus;
import com.mymvc.system.bus.event.ApplicationWebSocketEvent;
import com.mymvc.system.pojo.SocketTextMessagePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by alan.luo on 2017/11/6.
 *
 * SocketTextMessagePojo textMessagePojo = new SocketTextMessagePojo();
 * textMessagePojo.setToken(getToken());
 * textMessagePojo.setMessage(new TextMessage(request.getParameter("info")));
 * publisher.publishEvent(new ApplicationWebSocketEvent(textMessagePojo));
 */
@EventBus
public class WebSocketEventListener implements ApplicationListener<ApplicationWebSocketEvent> {

    @Autowired()
    private MessageWebSocketHandler webSocketHandler;

    @Async
    @Override
    public void onApplicationEvent(ApplicationWebSocketEvent applicationWebSocketEvent) {
        SocketTextMessagePojo textMessagePojo = (SocketTextMessagePojo) applicationWebSocketEvent.getSource();
        if (textMessagePojo.getToken() != null){
            webSocketHandler.sendMessageToUser(textMessagePojo.getToken(),textMessagePojo.getMessage());
        }
    }
}
