package com.mymvc.repository.hibernate.service.impl;

import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.dao.ArticleRepository;
import com.mymvc.repository.hibernate.pojo.Article;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.IArticleService2;
import com.mymvc.repository.hibernate.service.resource.IUserTokenService2;
import com.mymvc.system.annotation.ServiceHibernate;
import com.mymvc.system.basic.ExceptionError;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;
import com.mymvc.system.utils.CommonUtil;
import com.mymvc.system.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;

/**
 * Created by alan.luo on 2017/11/3.
 */
@ServiceHibernate
@Transactional
public class ArticleServiceImpl2 implements IArticleService2 {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private IUserTokenService2 tokenService2;

    @Override
    public Article getById(int id) throws IllegalServiceException {
        return parser(repository.getRowById(id));
    }

    @Override
    public PagePojo<Article> getByUserId(int page,int userId) throws IllegalServiceException {
        PagePojo<Article> pagePojo = repository.getByUserId(page,userId);
        if (pagePojo.getItemNum() > 0){
            for (Article article:pagePojo.getList()){
                parser(article);
            }
        }
        return pagePojo;
    }

    @Override
    public int save(Article o) throws IllegalServiceException {
        repository.add(o);
        return o.getId();
    }

    @Override
    public void update(Article o,String token) throws IllegalServiceException {
        Article article = this.authorizedAccess(o.getId(),token);

        if (o.getCommentCount() == null){
            o.setCommentCount(article.getCommentCount());
        }
        if (o.getViewCount() == null){
            o.setViewCount(article.getViewCount());
        }
        if (o.getCreateTime() == null){
            o.setCreateTime(article.getCreateTime());
        }
        if (o.getIsDelete() == null){
            o.setIsDelete(article.getIsDelete());
        }
        if (o.getStatus() == null){
            o.setStatus(article.getStatus());
        }
        if (o.getTitle() == null){
            o.setTitle(article.getTitle());
        }
        if (o.getContent() == null){
            o.setContent(article.getContent());
        }
        if (o.getUserId() == null){
            o.setUserId(article.getUserId());
        }
        repository.update(o);
    }

    @Override
    public void updateViewCount(Article o) throws IllegalServiceException {
        repository.update(o);
    }

    @Override
    public void updateCommentCount(int id) throws IllegalServiceException {
        repository.updateCommentCount(id);
    }

    @Override
    public int remove(int id,String token) throws IllegalServiceException {
        this.authorizedAccess(id,token);
        return repository.removeById(id);
    }

    @Override
    public PagePojo<Article> getAll(int p, List<PredicatePojo> where, String order, boolean isDesc) throws IllegalServiceException {
        PagePojo<Article> pagePojo = repository.search(p,where,order,isDesc);
        if (pagePojo.getItemNum() > 0){
            for (Article article:pagePojo.getList()){
                parser(article);
            }
        }
        return pagePojo;
    }

    private Article parser(Article a){
        if (a == null){
            return  a;
        }

        a.setCreateTimeFormat(DateUtil.getFullDateTime(a.getCreateTime()*1000L));
        a.setUpdateTimeFormat(DateUtil.getFullDateTime(a.getUpdateTime()*1000L));
        a.setAuthor(CommonUtil.parserUserInfoToHide(a.getAuthor()));
        return a;
    }


    /**
     * 权限鉴定
     * @param id
     * @param token
     * @throws IllegalServiceException
     */
    private Article authorizedAccess(int id,String token) throws IllegalServiceException {

        Article article = this.getById(id);
        if (article == null){
            throw new IllegalServiceException(ExceptionError.error_has_not_data);
        }

        UserToken userToken = tokenService2.getByUserToken(token);
        if (userToken.getStatus() == Status.invalid){
            throw new IllegalServiceException(ExceptionError.error_user_token_invalid);
        }

        if (userToken.getUserId() != article.getUserId()){
            throw new IllegalServiceException(ExceptionError.error_unauthorized_access);
        }

        return article;
    }



}
