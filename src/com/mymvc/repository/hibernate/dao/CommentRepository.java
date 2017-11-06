package com.mymvc.repository.hibernate.dao;

import com.mymvc.repository.hibernate.basic.AbstractDefaultRepository;
import com.mymvc.repository.hibernate.basic.Criteria;
import com.mymvc.repository.hibernate.pojo.Comment;
import com.mymvc.repository.hibernate.resource.ICommentRepository;
import com.mymvc.system.annotation.RepositoryHibernate;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan.luo on 2017/11/3.
 */
@RepositoryHibernate
public class CommentRepository extends AbstractDefaultRepository<Comment> implements ICommentRepository {

    @Override
    public PagePojo<Comment> getByUserId(int page, int userId) throws IllegalServiceException {

        List<PredicatePojo> list = new ArrayList<>();
        list.add(new PredicatePojo("userId",""+userId, Criteria.equal));

        return this.search(page,list,"id",true);
    }

    @Override
    public PagePojo<Comment> getByArticleId(int page, int articleId) throws IllegalServiceException {

        List<PredicatePojo> list = new ArrayList<>();
        list.add(new PredicatePojo("articleId",""+articleId, Criteria.equal));

        return this.search(page,list,"id",true);
    }

}
