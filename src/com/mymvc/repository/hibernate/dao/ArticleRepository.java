package com.mymvc.repository.hibernate.dao;

import com.mymvc.repository.hibernate.basic.AbstractDefaultRepository;
import com.mymvc.repository.hibernate.basic.Criteria;
import com.mymvc.repository.hibernate.pojo.Article;
import com.mymvc.repository.hibernate.resource.IArticleRepository;
import com.mymvc.system.annotation.RepositoryHibernate;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan.luo on 2017/11/3.
 */
@RepositoryHibernate
public class ArticleRepository extends AbstractDefaultRepository<Article> implements IArticleRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public PagePojo<Article> getByUserId(int page,int userId) throws IllegalServiceException {

        List<PredicatePojo> list = new ArrayList<>();
        list.add(new PredicatePojo("userId",""+userId, Criteria.equal));

        return this.search(page,list,"id",true);
    }

    @Override
    public void updateCommentCount(int id) throws IllegalServiceException {
        Query query = this.manager.createQuery("update Article set commentCount = commentCount + 1 where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

}
