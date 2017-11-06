package com.mymvc.system.bus;

import com.mymvc.service.resource.IUserService;
import com.mymvc.system.annotation.EventBus;
import com.mymvc.system.bus.event.LoginOutEvent;
import com.mymvc.system.exception.IllegalServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by alan.luo on 2017/10/17.
 */
@EventBus
public class LoginOutEventListener implements ApplicationListener<LoginOutEvent> {

    //@Autowired
    //private IUserService userService;

    @Async
    @Override
    public void onApplicationEvent(LoginOutEvent event) {
        System.out.println("LoginOutInterestedParty:"+event.getSource());

//        synchronized (this){
//            try {
//                userService.removeByUsername((String) event.getSource());
//            } catch (IllegalServiceException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
