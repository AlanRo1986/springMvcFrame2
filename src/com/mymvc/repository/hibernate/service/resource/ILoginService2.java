package com.mymvc.repository.hibernate.service.resource;

import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.system.exception.IllegalServiceException;

/**
 * Created by alan.luo on 2017/11/2.
 */
public interface ILoginService2 {

    User doLogin(String username, String password) throws IllegalServiceException;

    boolean doLoginOut(String token) throws IllegalServiceException;

}
