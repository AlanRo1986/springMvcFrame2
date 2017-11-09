package com.mymvc.app.controller;

import com.mymvc.constant.ConstantRedis;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.service.resource.IRedisMessageService;
import com.mymvc.socket.MessageWebSocketHandler;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.bus.event.ApplicationWebSocketEvent;
import com.mymvc.system.core.ResultResp;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.listener.RedisMessageListener;
import com.mymvc.system.pojo.SocketTextMessagePojo;
import com.mymvc.system.provider.CacheProvider;
import com.mymvc.system.utils.JsonUtil;
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

    @Autowired
    private IRedisMessageService messageService;

    @RequestMapping(value = "/send" ,method = RequestMethod.POST)
    public ResultResp<Void> send(HttpServletRequest request){
        ResultResp<Void> resp = new ResultResp<>();

        SocketTextMessagePojo textMessagePojo = new SocketTextMessagePojo();
        textMessagePojo.setToken(getToken());
        textMessagePojo.setMessage(new TextMessage(request.getParameter("info")));
        publisher.publishEvent(new ApplicationWebSocketEvent(textMessagePojo));



        return resp;
    }

    /**
     * redis sub&pub demo
     * @param request
     * @return
     */
    @RequestMapping(value = "/send2" )
    public ResultResp<Void> send2(HttpServletRequest request){
        ResultResp<Void> resp = new ResultResp<>();

        SocketTextMessagePojo textMessagePojo = new SocketTextMessagePojo();
        textMessagePojo.setToken(getToken());
        textMessagePojo.setMessage(new TextMessage(request.getParameter("info")));
        try {
            messageService.sendMessage(ConstantRedis.CHANNEL,JsonUtil.ObjectToJson(textMessagePojo));
        } catch (IllegalServiceException e) {
            e.printStackTrace();
        }
        return resp;
    }

    //redis sub&pub demo
//    @RequestMapping(value = "/send2" ,method = RequestMethod.GET)
//    public ResultResp<Void> send2(HttpServletRequest request){
//        ResultResp<Void> resp = new ResultResp<>();
//
//        User user = new User();
//        user.setUsername("test");
//        user.setEmail("ajsdfhakdsnfj");
//        try {
//            redisMessageService.sendMessage("message", JsonUtil.ObjectToJson(user));
//            user.setAge(12);
//            redisMessageService.sendMessage("message", JsonUtil.ObjectToJson(user));
//
//            cacheProvider.put("websocket1",user,0);
//
//        } catch (IllegalServiceException e) {
//            e.printStackTrace();
//        }
//
//        return resp;
//    }

}
