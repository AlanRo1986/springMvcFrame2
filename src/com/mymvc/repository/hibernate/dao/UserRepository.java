package com.mymvc.repository.hibernate.dao;

import com.mymvc.repository.hibernate.basic.AbstractDefaultRepository;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.repository.hibernate.resource.IUserRepository;
import com.mymvc.system.annotation.RepositoryHibernate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by alan.luo on 2017/10/22.
 */
@RepositoryHibernate
public class UserRepository extends AbstractDefaultRepository<User> implements IUserRepository{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User getRowByUserName(String name) {
        Query query = this.manager.createQuery("SELECT a from User a where username = :username");
        query.setParameter("username",name);
        if (query.getResultList().size() == 0){
            return null;
        }
        return (User) query.getSingleResult();
    }

}
