package com.mymvc.app.controller;

import com.mymvc.repository.hibernate.basic.Criteria;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.basic.UserLogType;
import com.mymvc.repository.hibernate.pojo.Comment;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.ICommentService2;
import com.mymvc.repository.hibernate.service.resource.IUserTokenService2;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.basic.ExceptionError;
import com.mymvc.system.basic.IDefaultControllerMethod;
import com.mymvc.system.bus.event.ArticleCommentEvent;
import com.mymvc.system.core.ResultResp;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.exception.IllegalValidateException;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;
import com.mymvc.system.provider.ValidatorProvider;
import com.mymvc.system.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alan.luo on 2017/11/3.
 */
@RestController
public class CommentController extends BasicApiController implements IDefaultControllerMethod<Comment> {

    @Autowired
    private ICommentService2 commentService2;

    @Autowired
    private IUserTokenService2 tokenService2;

    @Autowired
    private ValidatorProvider<Comment> validatorProvider;

    @Autowired
    private ApplicationEventPublisher publisher;


    @RequestMapping(value = "/comment",method = RequestMethod.GET)
    @Override
    public ResultResp<Map<String, Object>> _doGet(HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {

            String userId = request.getParameter("userId");
            String articleId = request.getParameter("articleId");
            String id = request.getParameter("id");

            if (StringUtils.isEmpty(articleId) && StringUtils.isEmpty(userId) && StringUtils.isEmpty(userId)){
                throw new IllegalValidateException(ExceptionError.error_argument_invalid);
            }
            Map<String, Object> map = new HashMap<>();

            if (StringUtils.isEmpty(id)){
                List<PredicatePojo> where = new ArrayList<>();
                if (!StringUtils.isEmpty(userId)){
                    where.add(new PredicatePojo("userId",userId, Criteria.equal));
                }
                if (!StringUtils.isEmpty(articleId)){
                    where.add(new PredicatePojo("articleId",articleId, Criteria.equal));
                }

                int p = 1;
                if (!StringUtils.isEmpty(request.getParameter("page"))){
                    p = Integer.parseInt(request.getParameter("page"));
                }

                PagePojo<Comment> page = commentService2.getAll(p,where,"id",true);
                map.put("page",page);

            }else {
                Comment comment = commentService2.getById(Integer.parseInt(id));
                map.put("comment",comment);
            }

            resp.setData(map);

        }catch (IllegalServiceException|IllegalValidateException e){
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @Override
    public ResultResp<Map<String, Object>> _doPost(@RequestBody Comment obj, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            validatorProvider.validate(obj);

            obj.setCreateTime(DateUtil.getTime()/1000);
            obj.setStatus(Status.valid);
            if (obj.getParentId() == null){
                obj.setParentId(0);
            }

            UserToken token = tokenService2.getByUserToken(getToken());
            obj.setUserId(token.getUserId());
            if (commentService2.save(obj) > 0){
                /**
                 * and save user login logs.
                 */
                UserLog userLog = new UserLog();
                userLog.setContent(getLang("info_post_comment_success",true));
                userLog.setUserId(obj.getUserId());
                userLog.setLogType(UserLogType.addComment);
                this.saveUserLog(userLog);

                /**
                 * 更新评论次数
                 */
                publisher.publishEvent(new ArticleCommentEvent(obj.getArticleId()));
                resp.setInfo("info_inserted_success");
            }




        }catch (IllegalValidateException|IllegalServiceException e){
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }

    @RequestMapping(value = "/comment/{id}",method = RequestMethod.PUT)
    @Override
    public ResultResp<Map<String, Object>> _doPut(@PathVariable Integer id, @RequestBody Comment obj, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            if (id <= 0){
                throw new IllegalValidateException(ExceptionError.error_argument_invalid);
            }

            obj.setId(id);
            commentService2.update(obj,getToken());
            resp.setInfo("info_updated_success");
        }catch (IllegalValidateException|IllegalServiceException e){
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;

    }

    @RequestMapping(value = "/comment/{id}",method = RequestMethod.DELETE)
    @Override
    public ResultResp<Map<String, Object>> _doDelete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            if (id <= 0){
                throw new IllegalValidateException(ExceptionError.error_argument_invalid);
            }

            commentService2.remove(id,getToken());
            resp.setInfo("info_deleted_success");

        }catch (IllegalValidateException|IllegalServiceException e){
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }
}
