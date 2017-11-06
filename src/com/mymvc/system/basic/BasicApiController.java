package com.mymvc.system.basic;

import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.IUserTokenService2;
import com.mymvc.system.bus.event.UserLogEvent;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * Created by alan.luo on 2017/9/18.
 */
@RequestMapping(value = "/api")
@RestController
public abstract class BasicApiController extends CompactController {

    @Autowired
    private IUserTokenService2 tokenService2;

    @Autowired
    private ApplicationEventPublisher publisher;

    public BasicApiController(){
        super(BasicApiController.class);
    }

    public BasicApiController(Class classz) {
        super(classz);
    }


    public void saveUserLog(UserLog userLog){

        /**
         * and save user login logs.
         */
        try {
            if (userLog.getUserId() <= 0){
                UserToken userToken = tokenService2.getByUserToken(getToken());
                if (userToken == null){
                    return;
                }
                userLog.setUserId(userToken.getUserId());
            }

            userLog.setIpAddr(CommonUtil.getClientIp(null));
            publisher.publishEvent(new UserLogEvent(userLog));

        } catch (IllegalServiceException e) {
            e.printStackTrace();
        }

    }



}
