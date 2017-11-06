package com.mymvc.repository.hibernate.service.impl;

import com.mymvc.repository.hibernate.dao.UserRepository;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.repository.hibernate.service.resource.IUserService2;
import com.mymvc.system.annotation.ServiceHibernate;
import com.mymvc.system.basic.ExceptionError;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;
import com.mymvc.system.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * use hibernate ORM get the DB.
 * Created by alan.luo on 2017/10/7.
 */
@ServiceHibernate
@Transactional
public class UserServiceImpl2 implements IUserService2 {

    @Autowired
    private UserRepository repository;

    @Override
    public User getById(int id) throws IllegalServiceException {
        return repository.getRowById(id);
    }

    @Override
    public User getByUserName(String userName) throws IllegalServiceException {
        return repository.getRowByUserName(userName);
    }

    @Override
    public int save(User user) throws IllegalServiceException {
        repository.add(user);
        return user.getId();
    }

    @Override
    public void update(User user) throws IllegalServiceException {
        User u = this.getById(user.getId());
        if (u == null){
            throw new IllegalServiceException(ExceptionError.error_has_not_data);
        }
        repository.update(user);
    }

    @Override
    public int remove(int id) throws IllegalServiceException {
        return repository.removeById(id);
    }


    /**
     * List<PredicatePojo> where = new ArrayList<>();
     * where.add(new PredicatePojo("id","81", Criteria.lt));
     * where.add(new PredicatePojo("age","10", Criteria.gt));
     *
     * List<PredicatePojo> where = new ArrayList<>();
     * if (userName != null){
     * userName = "%" + userName + "%";
     * List<PredicatePojo> like = new ArrayList<>();
     * like.add(new PredicatePojo("username",userName, Criteria.like));
     * like.add(new PredicatePojo("mobile",userName, Criteria.like));
     * like.add(new PredicatePojo("email",userName, Criteria.like));
     *
     * PredicatePojo pojo = new PredicatePojo();
     * pojo.setLikeObj(like);
     * pojo.setCriteria(Criteria.like);
     * where.add(pojo);
     * }
     *
     * @param where
     * @param order
     * @param isDesc
     * @return
     * @throws IllegalServiceException
     */
    @Override
    public PagePojo<User> getAll(int p,List<PredicatePojo> where,String order,boolean isDesc) throws IllegalServiceException {
        if (order == null){
            order = "id";
        }
        PagePojo<User> page =  repository.search(p,where,order,isDesc);
        if (page.getList() != null){
            for (User u:page.getList()){
                u.setCreateTimeFormat(DateUtil.getFullDateTime(u.getCreateTime()*1000));
            }
        }

        return page;
    }





}
