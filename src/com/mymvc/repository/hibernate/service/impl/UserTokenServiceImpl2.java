package com.mymvc.repository.hibernate.service.impl;

import com.mymvc.repository.hibernate.dao.UserTokenRepository;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.IUserTokenService2;
import com.mymvc.system.basic.ExceptionError;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;
import com.mymvc.system.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alan.luo on 2017/11/2.
 */
@Service
@Transactional
public class UserTokenServiceImpl2 implements IUserTokenService2 {


    @Autowired
    private UserTokenRepository repository;

    @Override
    public UserToken getById(int id) throws IllegalServiceException {
        return repository.getRowById(id);
    }

    @Override
    public UserToken getByUserId(int userId) throws IllegalServiceException {
        return repository.getByUserId(userId);
    }

    @Override
    public UserToken getByUserToken(String token) throws IllegalServiceException {
        if (token == null){
            throw new IllegalServiceException(ExceptionError.error_need_logined);
        }
        return repository.getByUserToken(token);
    }

    @Override
    public int save(UserToken token) throws IllegalServiceException {
        UserToken userToken = this.getByUserId(token.getUserId());
        if (userToken == null){
            repository.add(token);
        }else {
            token.setId(userToken.getId());
            this.update(token);
        }
        return token.getId();
    }

    @Override
    public void update(UserToken token) throws IllegalServiceException {
        repository.update(token);
    }

    @Override
    public int remove(int id) throws IllegalServiceException {
        return repository.removeById(id);
    }

    @Override
    public PagePojo<UserToken> getAll(int p, List<PredicatePojo> where, String order, boolean isDesc) throws IllegalServiceException {
        PagePojo<UserToken> pagePojo = repository.search(p,where,order,isDesc);
        if (pagePojo.getList() != null){
            for (UserToken u:pagePojo.getList()){
                parser(u);
            }
        }
        return pagePojo;
    }

    /**
     * parser date time.
     * @param u
     * @return
     */
    private UserToken parser(UserToken u) {
        u.setLoginTimeFormat(DateUtil.getFullDateTime(u.getLoginTime()*1000));
        u.setExpireInFormat(DateUtil.getFullDateTime(u.getExpireIn()*1000));
        return u;
    }
}
