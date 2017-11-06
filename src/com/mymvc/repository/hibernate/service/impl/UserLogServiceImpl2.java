package com.mymvc.repository.hibernate.service.impl;

import com.mymvc.repository.hibernate.dao.UserLogRepository;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.service.resource.IUserLogService2;
import com.mymvc.system.annotation.ServiceHibernate;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;
import com.mymvc.system.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alan.luo on 2017/11/2.
 */

@ServiceHibernate
@Transactional
public class UserLogServiceImpl2 implements IUserLogService2 {

    @Autowired
    private UserLogRepository repository;


    @Override
    public UserLog getById(int id) throws IllegalServiceException {
        return parser(repository.getRowById(id));
    }

    @Override
    public PagePojo<UserLog> getByUserId(int page,int userId) throws IllegalServiceException {
        PagePojo<UserLog> pagePojo = repository.getByUserId(page,userId);
        if (pagePojo.getList() != null){
            for (UserLog u:pagePojo.getList()){
                parser(u);
            }
        }
        return pagePojo;
    }

    @Override
    public int save(UserLog userLog) throws IllegalServiceException {
        repository.add(userLog);
        return userLog.getId();
    }

    @Override
    public void update(UserLog userLog) throws IllegalServiceException {
        repository.update(userLog);
    }

    @Override
    public int remove(int id) throws IllegalServiceException {
        return repository.removeById(id);
    }

    @Override
    public PagePojo<UserLog> getAll(int p,List<PredicatePojo> where, String order, boolean isDesc) throws IllegalServiceException {
        PagePojo<UserLog> pagePojo = repository.search(p,where,order,isDesc);
        if (pagePojo.getList() != null){
            for (UserLog u:pagePojo.getList()){
                parser(u);
            }
        }
        return pagePojo;
    }

    private UserLog parser(UserLog u){
        if (u == null){
            return u;
        }

        u.setCreateTimeFormat(DateUtil.getFullDateTime(u.getCreateTime()*1000));
        return u;
    }
}
