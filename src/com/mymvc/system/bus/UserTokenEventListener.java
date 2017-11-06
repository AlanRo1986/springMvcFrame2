package com.mymvc.system.bus;

import com.mymvc.repository.hibernate.basic.Delete;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.IUserLogService2;
import com.mymvc.repository.hibernate.service.resource.IUserTokenService2;
import com.mymvc.system.annotation.EventBus;
import com.mymvc.system.bus.event.UserLogEvent;
import com.mymvc.system.bus.event.UserTokenEvent;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alan.luo on 2017/11/2.
 */
@EventBus
public class UserTokenEventListener implements ApplicationListener<UserTokenEvent> {

    @Autowired
    private IUserTokenService2 serviceImpl;


    /**
     * save user token.
     *
     * @param userTokenEvent
     */
    @Async
    @Override
    public void onApplicationEvent(UserTokenEvent userTokenEvent) {

        UserToken token = (UserToken) userTokenEvent.getSource();

        try {
            serviceImpl.save(token);
        } catch (IllegalServiceException e) {
            e.printStackTrace();
        }
    }
}
