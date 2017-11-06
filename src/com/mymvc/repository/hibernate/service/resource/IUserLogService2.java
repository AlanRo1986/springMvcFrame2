package com.mymvc.repository.hibernate.service.resource;

import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan.luo on 2017/11/2.
 */

public interface IUserLogService2 {

    UserLog getById(int id) throws IllegalServiceException;

    PagePojo<UserLog> getByUserId(int page,int userId) throws IllegalServiceException;

    int save(UserLog userLog) throws IllegalServiceException;

    void update(UserLog userLog) throws IllegalServiceException;

    int remove(int id) throws IllegalServiceException;

    PagePojo<UserLog> getAll(int p,List<PredicatePojo> where, String order, boolean isDesc) throws IllegalServiceException;

}
