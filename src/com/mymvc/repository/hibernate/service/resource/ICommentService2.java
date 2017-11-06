package com.mymvc.repository.hibernate.service.resource;

import com.mymvc.repository.hibernate.pojo.Article;
import com.mymvc.repository.hibernate.pojo.Comment;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;

import java.util.List;

/**
 * Created by alan.luo on 2017/11/3.
 */
public interface ICommentService2 {

    Comment getById(int id) throws IllegalServiceException;

    PagePojo<Comment> getByArticleId(int page, int articleId) throws IllegalServiceException;

    PagePojo<Comment> getByUserId(int page, int userId) throws IllegalServiceException;

    int save(Comment o) throws IllegalServiceException;

    void update(Comment o,String token) throws IllegalServiceException;

    int remove(int id,String token) throws IllegalServiceException;

    PagePojo<Comment> getAll(int p, List<PredicatePojo> where, String order, boolean isDesc) throws IllegalServiceException;

}
