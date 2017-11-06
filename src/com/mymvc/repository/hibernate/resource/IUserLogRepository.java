package com.mymvc.repository.hibernate.resource;

import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;

/**
 * Created by alan.luo on 2017/11/2.
 */

public interface IUserLogRepository {
    PagePojo<UserLog> getByUserId(int page,int userId) throws IllegalServiceException;
}
