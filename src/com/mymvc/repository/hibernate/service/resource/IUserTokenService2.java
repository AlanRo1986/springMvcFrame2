package com.mymvc.repository.hibernate.service.resource;

import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan.luo on 2017/11/2.
 */
public interface IUserTokenService2 {

    UserToken getById(int id) throws IllegalServiceException;

    UserToken getByUserId(int userId) throws IllegalServiceException;

    UserToken getByUserToken(String token) throws IllegalServiceException;

    int save(UserToken token) throws IllegalServiceException;

    void update(UserToken token) throws IllegalServiceException;

    int remove(int id) throws IllegalServiceException;

    PagePojo<UserToken> getAll(int p, List<PredicatePojo> where, String order, boolean isDesc) throws IllegalServiceException;

}
