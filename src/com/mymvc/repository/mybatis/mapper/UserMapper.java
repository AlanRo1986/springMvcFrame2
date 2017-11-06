package com.mymvc.repository.mybatis.mapper;

import com.mymvc.model.UserModel;

import java.util.List;

/**
 * Created by alan.luo on 2017/10/18.
 */
public interface UserMapper {

    List<UserModel> getAll();

    UserModel getRowById(Integer id);

    int save(UserModel user);
}
