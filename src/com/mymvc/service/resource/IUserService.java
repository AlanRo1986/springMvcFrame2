package com.mymvc.service.resource;

import com.mymvc.model.UserModel;
import com.mymvc.system.exception.IllegalServiceException;


import javax.validation.constraints.Min;
import javax.validation.executable.ValidateOnExecution;
import java.util.List;

/**
 * Created by alan.luo on 2017/10/7.
 */
public interface IUserService {

    int save(UserModel user) throws IllegalServiceException;

    UserModel get(int id) throws IllegalServiceException;

    List<UserModel> getAll() throws IllegalServiceException;

    void removeByUsername(String userName) throws IllegalServiceException;
}
