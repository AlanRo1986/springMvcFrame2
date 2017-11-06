package com.mymvc.service.impl;

import com.mymvc.model.UserModel;
import com.mymvc.repository.mybatis.mapper.UserMapper;
import com.mymvc.service.resource.IUserService;
import com.mymvc.system.basic.CompactService;
import com.mymvc.system.exception.IllegalServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alan.luo on 2017/10/7.
 */
@Transactional
@Service
public class UserServiceImpl extends CompactService implements IUserService {

    //@Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {
        super(UserServiceImpl.class);
    }

    @Override
    public int save(UserModel user) throws IllegalServiceException {
        int a = 0;

        return userMapper.save(user);


    }

    @Override
    public UserModel get(int id) throws IllegalServiceException {
        return userMapper.getRowById(id);
    }

    @Override
    public List<UserModel> getAll() throws IllegalServiceException {
        return userMapper.getAll();
    }

    @Override
    public void removeByUsername(String userName) throws IllegalServiceException {

    }
}
