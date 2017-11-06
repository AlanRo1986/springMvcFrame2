package com.mymvc.repository.hibernate.resource;

import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.system.exception.IllegalServiceException;

/**
 * Created by alan.luo on 2017/11/2.
 */

public interface IUserTokenRepository {

    UserToken getByUserId(int userId) throws IllegalServiceException;

    UserToken getByUserToken(String token) throws IllegalServiceException;

}
