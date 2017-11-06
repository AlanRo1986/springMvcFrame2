package com.mymvc.app.controller;

import com.mymvc.socket.MessageWebSocketHandler;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.bus.event.ApplicationWebSocketEvent;
import com.mymvc.system.core.ResultResp;
import com.mymvc.system.pojo.SocketTextMessagePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alan.luo on 2017/11/6.
 */
@RestController
public class WebsocketController extends BasicApiController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping(value = "/send" ,method = RequestMethod.POST)
    public ResultResp<Void> send(HttpServletRequest request){
        ResultResp<Void> resp = new ResultResp<>();

        SocketTextMessagePojo textMessagePojo = new SocketTextMessagePojo();
        textMessagePojo.setToken(getToken());
        textMessagePojo.setMessage(new TextMessage(request.getParameter("info")));
        publisher.publishEvent(new ApplicationWebSocketEvent(textMessagePojo));

        return resp;
    }
}
