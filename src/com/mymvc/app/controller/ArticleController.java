package com.mymvc.app.controller;

import com.mymvc.repository.hibernate.basic.Criteria;
import com.mymvc.repository.hibernate.basic.Delete;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.basic.UserLogType;
import com.mymvc.repository.hibernate.pojo.Article;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.IArticleService2;
import com.mymvc.repository.hibernate.service.resource.IUserTokenService2;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.basic.ExceptionError;
import com.mymvc.system.basic.IDefaultControllerMethod;
import com.mymvc.system.core.ResultResp;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.exception.IllegalValidateException;
import com.mymvc.system.listener.RequestListener;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;
import com.mymvc.system.provider.ValidatorProvider;
import com.mymvc.system.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alan.luo on 2017/10/7.
 */
@RestController
public class ArticleController extends BasicApiController implements IDefaultControllerMethod<Article> {

    @Autowired
    private ValidatorProvider<Article> validatorProvider;

    @Autowired
    private IArticleService2 articleService2;

    @Autowired
    private IUserTokenService2 tokenService2;

    @RequestMapping(value = "/article",method = RequestMethod.GET)
    @Override
    public ResultResp<Map<String, Object>> _doGet(HttpServletRequest request, HttpServletResponse response) {
        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {

            System.out.println(RequestListener.getInstance().getClientIp());

            String userId = request.getParameter("userId");
            String id = request.getParameter("id");
            String keyWords = request.getParameter("keywords");

            Map<String, Object> map = new HashMap<>();

            if (StringUtils.isEmpty(id)){
                List<PredicatePojo> where = new ArrayList<>();
                if (!StringUtils.isEmpty(keyWords)){
                    keyWords = "%"+keyWords+"%";
                    List<PredicatePojo> like = new ArrayList<>();
                    like.add(new PredicatePojo("title",keyWords, Criteria.like));
                    like.add(new PredicatePojo("content",keyWords, Criteria.like));
                    where.add(new PredicatePojo(like,Criteria.like));
                }

                if (!StringUtils.isEmpty(userId)){
                    where.add(new PredicatePojo("userId",userId, Criteria.equal));
                }

                int p = 1;
                if (!StringUtils.isEmpty(request.getParameter("page"))){
                    p = Integer.parseInt(request.getParameter("page"));
                }

                PagePojo<Article> page = articleService2.getAll(p,where,"updateTime",true);
                map.put("page",page);

            }else {
                Article article = articleService2.getById(Integer.parseInt(id));
                if (article != null){
                    article.setViewCount(article.getViewCount()+1);
                    articleService2.updateViewCount(article);
                }

                map.put("article",article);
            }

            resp.setData(map);

        }catch (IllegalServiceException e){
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }

    @RequestMapping(value = "/article",method = RequestMethod.POST)
    @Override
    public ResultResp<Map<String, Object>> _doPost(@RequestBody Article o, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            validatorProvider.validate(o);

            UserToken token = tokenService2.getByUserToken(getToken());
            o.setUserId(token.getUserId());
            o.setCommentCount(0);
            o.setViewCount(0);
            o.setCreateTime(DateUtil.getTime()/1000);
            o.setIsDelete(Delete.normal);
            o.setStatus(Status.valid);
            o.setUpdateTime(o.getCreateTime());

            if (articleService2.save(o) > 0){

                /**
                 * and save user login logs.
                 */
                UserLog userLog = new UserLog();
                userLog.setContent(getLang("info_post_article_success",true));
                userLog.setUserId(o.getUserId());
                userLog.setLogType(UserLogType.addArticle);
                this.saveUserLog(userLog);

                resp.setInfo("info_inserted_success");

            }else {
                throw new IllegalServiceException(ExceptionError.error_inserted);
            }

        } catch (IllegalValidateException|IllegalServiceException e) {
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }
        return resp;

    }

    @RequestMapping(value = "/article/{id}",method = RequestMethod.PUT)
    @Override
    public ResultResp<Map<String, Object>> _doPut(@PathVariable Integer id, @RequestBody Article o, HttpServletRequest request, HttpServletResponse response) {
        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            if (id <= 0){
                throw new IllegalValidateException(ExceptionError.error_argument_invalid);
            }

            o.setId(id);
            o.setUpdateTime(DateUtil.getTime()/1000);
            articleService2.update(o,getToken());
            resp.setInfo("info_updated_success");

        }catch (IllegalValidateException|IllegalServiceException e){
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }

    @RequestMapping(value = "/article/{id}",method = RequestMethod.DELETE)
    @Override
    public ResultResp<Map<String, Object>> _doDelete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            if (id <= 0){
                throw new IllegalValidateException(ExceptionError.error_argument_invalid);
            }

            articleService2.remove(id,getToken());
            resp.setInfo("info_deleted_success");

        }catch (IllegalValidateException|IllegalServiceException e){
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;

    }


}
