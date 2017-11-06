package com.mymvc.repository.hibernate.service.resource;

import com.mymvc.repository.hibernate.pojo.Article;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;

import java.util.List;

/**
 * Created by alan.luo on 2017/11/3.
 */
public interface IArticleService2 {

    Article getById(int id) throws IllegalServiceException;

    PagePojo<Article> getByUserId(int page,int userId) throws IllegalServiceException;

    int save(Article o) throws IllegalServiceException;

    void update(Article o,String token) throws IllegalServiceException;

    void updateViewCount(Article o) throws IllegalServiceException;

    void updateCommentCount(int id) throws IllegalServiceException;

    int remove(int id,String token) throws IllegalServiceException;

    PagePojo<Article> getAll(int p, List<PredicatePojo> where, String order, boolean isDesc) throws IllegalServiceException;

}
