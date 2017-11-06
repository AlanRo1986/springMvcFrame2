package com.mymvc.repository.hibernate.resource;

import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.system.exception.IllegalServiceException;


/**
 * Created by alan.luo on 2017/10/22.
 */
public interface IUserRepository {

    User getRowByUserName(String name) throws IllegalServiceException;

}
