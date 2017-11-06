package com.mymvc.repository.hibernate.dao;

import com.mymvc.repository.hibernate.basic.AbstractDefaultRepository;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.resource.IUserTokenRepository;
import com.mymvc.system.annotation.RepositoryHibernate;
import com.mymvc.system.exception.IllegalServiceException;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by alan.luo on 2017/11/2.
 */

@RepositoryHibernate
public class UserTokenRepository extends AbstractDefaultRepository<UserToken> implements IUserTokenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public UserToken getByUserId(int userId) throws IllegalServiceException {
        Query query = this.manager.createQuery("SELECT a  from UserToken a where userId = :userId");
        query.setParameter("userId",userId);
        if (query.getResultList().size() == 0){
            return null;
        }
        return (UserToken) query.getSingleResult();
    }

    @Override
    public UserToken getByUserToken(String token) throws IllegalServiceException {
        Query query = this.manager.createQuery("SELECT a from UserToken a where token = :token");
        query.setParameter("token",token);
        if (query.getResultList().size() == 0){
            return null;
        }
        return (UserToken) query.getSingleResult();
    }


}
