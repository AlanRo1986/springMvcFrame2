package com.mymvc.repository.hibernate.service.impl;

import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.dao.CommentRepository;
import com.mymvc.repository.hibernate.pojo.Comment;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.ICommentService2;
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

import java.util.List;

/**
 * Created by alan.luo on 2017/11/3.
 */
@ServiceHibernate
@Transactional
public class CommentServiceImpl2 implements ICommentService2 {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private IUserTokenService2 tokenService2;

    @Override
    public Comment getById(int id) throws IllegalServiceException {
        return parser(repository.getRowById(id));
    }

    @Override
    public PagePojo<Comment> getByArticleId(int page, int articleId) throws IllegalServiceException {
        PagePojo<Comment> pagePojo = repository.getByArticleId(page,articleId);
        if (pagePojo.getItemNum() > 0){
            for (Comment c:pagePojo.getList()){
                parser(c);
            }
        }
        return pagePojo;
    }

    @Override
    public PagePojo<Comment> getByUserId(int page, int userId) throws IllegalServiceException {
        PagePojo<Comment> pagePojo = repository.getByUserId(page,userId);
        if (pagePojo.getItemNum() > 0){
            for (Comment c:pagePojo.getList()){
                parser(c);
            }
        }
        return pagePojo;
    }

    @Override
    public int save(Comment o) throws IllegalServiceException {
        repository.add(o);
        return o.getId();
    }

    @Override
    public void update(Comment o,String token) throws IllegalServiceException {
        Comment comment = this.authorizedAccess(o.getId(),token);
        if (o.getUserId() == null){
            o.setUserId(comment.getUserId());
        }
        if (o.getParentId() == null){
            o.setParentId(comment.getParentId());
        }
        if (o.getArticleId() == null){
            o.setArticleId(comment.getArticleId());
        }
        if (o.getCreateTime() == null){
            o.setCreateTime(comment.getCreateTime());
        }
        if (o.getContent() == null){
            o.setContent(comment.getContent());
        }
        if (o.getStatus() == null){
            o.setStatus(comment.getStatus());
        }
        repository.update(o);
    }

    @Override
    public int remove(int id,String token) throws IllegalServiceException {
        this.authorizedAccess(id,token);
        return repository.removeById(id);
    }

    @Override
    public PagePojo<Comment> getAll(int p, List<PredicatePojo> where, String order, boolean isDesc) throws IllegalServiceException {
        PagePojo<Comment> pagePojo = repository.search(p,where,order,isDesc);
        if (pagePojo.getItemNum() > 0){
            for (Comment c:pagePojo.getList()){
                parser(c);
            }
        }
        return pagePojo;
    }

    private Comment parser(Comment o){
        if(o == null){
            return o;
        }
        o.setCreateTimeFormat(DateUtil.getFullDateTime(o.getCreateTime()*1000L));
        o.setAuthor(CommonUtil.parserUserInfoToHide(o.getAuthor()));
        return o;
    }

    /**
     * 权限鉴定
     * @param id
     * @param token
     * @throws IllegalServiceException
     */
    private Comment authorizedAccess(int id,String token) throws IllegalServiceException {

        Comment comment = this.getById(id);
        if (comment == null){
            throw new IllegalServiceException(ExceptionError.error_has_not_data);
        }

        UserToken userToken = tokenService2.getByUserToken(token);
        if (userToken.getStatus() == Status.invalid){
            throw new IllegalServiceException(ExceptionError.error_user_token_invalid);
        }

        if (userToken.getUserId() != comment.getUserId()){
            throw new IllegalServiceException(ExceptionError.error_unauthorized_access);
        }

        return comment;

    }
}
