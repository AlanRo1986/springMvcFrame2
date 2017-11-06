package com.mymvc.system.bus;

import com.mymvc.repository.hibernate.basic.Delete;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.service.resource.IUserLogService2;
import com.mymvc.system.annotation.EventBus;
import com.mymvc.system.bus.event.UserLogEvent;
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
public class UserLogEventListener implements ApplicationListener<UserLogEvent> {

    @Autowired
    private IUserLogService2 serviceImpl;

    /**
     * save user logs.
     *
     * ++++++++++++++++++++++++++++++++++++++++++++++++
     * @Autowired
     * private ApplicationEventPublisher publisher;
     *
     *
     * UserLog userLog = new UserLog();
     * userLog.setUserId(user.getId());
     * userLog.setContent(getLang("info_user_registered",true));
     * userLog.setLogType(UserLogType.registered);
     * userLog.setIpAddr(CommonUtil.getClientIp(request));
     * publisher.publishEvent(new UserLogEvent(userLog));
     *
     * @param userLogEvent
     */
    @Async
    @Override
    public void onApplicationEvent(UserLogEvent userLogEvent) {

        UserLog userLog = (UserLog) userLogEvent.getSource();
        userLog.setCreateTime(DateUtil.getTime()/1000);
        userLog.setStatus(Status.valid);
        userLog.setIsDelete(Delete.normal);
        try {
            serviceImpl.save(userLog);
        } catch (IllegalServiceException e) {
            e.printStackTrace();
        }
    }
}
