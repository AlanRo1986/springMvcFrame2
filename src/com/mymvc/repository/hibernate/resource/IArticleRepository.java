package com.mymvc.repository.hibernate.resource;

import com.mymvc.repository.hibernate.pojo.Article;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;

/**
 * Created by alan.luo on 2017/11/3.
 */
public interface IArticleRepository {

    PagePojo<Article> getByUserId(int page,int userId) throws IllegalServiceException;

    void updateCommentCount(int id) throws IllegalServiceException;

}
