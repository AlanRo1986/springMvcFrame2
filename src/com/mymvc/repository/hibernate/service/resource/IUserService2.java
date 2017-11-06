package com.mymvc.repository.hibernate.service.resource;

import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;

import java.util.List;

/**
 * Created by alan.luo on 2017/10/7.
 */
public interface IUserService2 {

    User getById(int id) throws IllegalServiceException;

    User getByUserName(String username) throws IllegalServiceException;

    int save(User user) throws IllegalServiceException;

    void update(User user) throws IllegalServiceException;

    int remove(int id) throws IllegalServiceException;

    PagePojo<User> getAll(int p,List<PredicatePojo> where,String order,boolean isDesc) throws IllegalServiceException;

}
