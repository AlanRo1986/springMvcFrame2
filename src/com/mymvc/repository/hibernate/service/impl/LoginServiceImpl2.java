package com.mymvc.repository.hibernate.service.impl;

import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.ILoginService2;
import com.mymvc.repository.hibernate.service.resource.IUserService2;
import com.mymvc.repository.hibernate.service.resource.IUserTokenService2;
import com.mymvc.system.annotation.ServiceHibernate;
import com.mymvc.system.basic.ExceptionError;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.provider.SecurityProvider;
import com.mymvc.system.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alan.luo on 2017/11/2.
 */
@ServiceHibernate
public class LoginServiceImpl2 implements ILoginService2 {

    @Autowired
    private IUserService2 service2;

    @Autowired
    private IUserTokenService2 tokenService2;

    @Autowired
    private SecurityProvider securityProvider;

    @Override
    public User doLogin(String username, String password) throws IllegalServiceException {

        User user = service2.getByUserName(username);

        if (user == null){
            throw new IllegalServiceException(ExceptionError.error_has_not_username);
        }

        if (password.equals(user.getLoginPassword())){
            user.setToken(securityProvider.makeUserToken(username+password));
            return user;
        }else {
            throw new IllegalServiceException(ExceptionError.error_user_password_invalid);
        }

    }

    @Override
    public boolean doLoginOut(String token) throws IllegalServiceException {
        if (token == null){
            throw new IllegalServiceException(ExceptionError.error_has_not_logined);
        }

        UserToken userToken = tokenService2.getByUserToken(token);
        if (userToken == null){
            throw new IllegalServiceException(ExceptionError.error_has_not_logined);
        }

        userToken.setStatus(Status.invalid);
        userToken.setExpireIn(DateUtil.getTime() / 1000 - 1);

        tokenService2.update(userToken);

        return true;
    }

}
