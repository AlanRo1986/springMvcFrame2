package com.mymvc.system.bus;

import com.mymvc.model.UserModel;
import com.mymvc.service.resource.IUserService;
import com.mymvc.system.annotation.EventBus;
import com.mymvc.system.bus.event.LoginEvent;
import com.mymvc.system.exception.IllegalServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by alan.luo on 2017/10/17.
 */
@EventBus
public class LoginEventListener implements ApplicationListener<LoginEvent> {

    @Autowired
    private IUserService userService;

    private int userId = 0;

    @Async
    @Override
    public void onApplicationEvent(LoginEvent event) {
        UserModel userModel = (UserModel) event.getSource();
        System.out.println("LoginInterestedParty:"+userModel.getUsername());

        synchronized (this){
            userId++;
            userModel.setId(userId);

            try {
                userService.save(userModel);
            } catch (IllegalServiceException e) {
                e.printStackTrace();
            }
        }

    }
}
