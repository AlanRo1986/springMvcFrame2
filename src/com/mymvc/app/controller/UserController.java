package com.mymvc.app.controller;

import com.mymvc.repository.hibernate.basic.Criteria;
import com.mymvc.repository.hibernate.basic.Gender;
import com.mymvc.repository.hibernate.basic.UserLogType;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.service.resource.IUserService2;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.basic.ExceptionError;
import com.mymvc.system.basic.IDefaultControllerMethod;
import com.mymvc.system.bus.event.UserLogEvent;
import com.mymvc.system.core.Application;
import com.mymvc.system.core.ResultResp;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.exception.IllegalValidateException;
import com.mymvc.system.pojo.PredicatePojo;
import com.mymvc.system.provider.ValidatorProvider;
import com.mymvc.system.utils.CommonUtil;
import com.mymvc.system.utils.DateUtil;
import com.mymvc.system.utils.Md5Util;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alan.luo on 2017/9/20.
 */
@RestController
public class UserController extends BasicApiController implements IDefaultControllerMethod<User> {

    @Autowired
    private ValidatorProvider<User> validatorProvider;

    @Autowired
    private IUserService2 userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @Override
    public ResultResp<Map<String, Object>> _doGet(HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        String  id = request.getParameter("id");
        String  userName = request.getParameter("username");

        int page = 1;
        if (request.getParameter("page") != null){
            page = Integer.valueOf(request.getParameter("page"));
        }

        try {
            Map<String,Object> map = new HashMap<>();
            if (id == null){
                List<PredicatePojo> where = new ArrayList<>();
                if (userName != null){
                    userName = "%" + userName + "%";
                    List<PredicatePojo> like = new ArrayList<>();
                    like.add(new PredicatePojo("username",userName, Criteria.like));
                    like.add(new PredicatePojo("mobile",userName, Criteria.like));
                    like.add(new PredicatePojo("email",userName, Criteria.like));

                    PredicatePojo pojo = new PredicatePojo();
                    pojo.setLikeObj(like);
                    pojo.setCriteria(Criteria.like);
                    where.add(pojo);
                }
                map.put("user",userService.getAll(page,where,"id",true));
            }else{
                User userModel = userService.getById(Integer.parseInt(id));
                map.put("user",userModel);
            }

            resp.setData(map);

        } catch (IllegalServiceException e) {
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @Override
    public ResultResp<Map<String, Object>> _doPost(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            /**
             * 验证用户数据
             */
            validatorProvider.validate(user);


            if (user.getLoginPassword() != null){
                user.setLoginPassword(Md5Util.md5(user.getLoginPassword()));
            }
            user.setCreateTime(DateUtil.getTime()/1000);
            user.setSex(Gender.UNSPECIFIED);

            if(userService.save(user) > 0){
                resp.setInfo("info_user_registered");

                /**
                 * publisher a message to userLog DB.
                 */
                UserLog userLog = new UserLog();
                userLog.setUserId(user.getId());
                userLog.setContent(getLang("info_user_registered",true));
                userLog.setLogType(UserLogType.registered);
                userLog.setIpAddr(CommonUtil.getClientIp(request));
                publisher.publishEvent(new UserLogEvent(userLog));
            }else {
                throw new IllegalValidateException(ExceptionError.error_user_registered);
            }


        } catch (IllegalValidateException|IllegalServiceException e) {
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    @Override
    public ResultResp<Map<String, Object>> _doPut(@PathVariable Integer id,@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            if (id == null){
                throw new IllegalValidateException("error_validate_id");
            }

            if (user.getLoginPassword() != null){
                user.setLoginPassword(Md5Util.md5(user.getLoginPassword()));
            }
            user.setId(Integer.valueOf(id));

            userService.update(user);
            resp.setInfo("info_updated_success");

        } catch (IllegalValidateException|IllegalServiceException e) {
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    @Override
    public ResultResp<Map<String, Object>> _doDelete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            if (id == null){
                throw new IllegalValidateException("error_validate_id");
            }

            if (userService.remove(Integer.valueOf(id)) > 0){
                resp.setInfo("info_deleted_success");
            }else {
                resp.setInfo("error_deleted");
            }

        } catch (IllegalValidateException|IllegalServiceException e) {
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;

    }
}
