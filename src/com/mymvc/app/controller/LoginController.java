package com.mymvc.app.controller;

import com.mymvc.constant.Constant;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.basic.UserLogType;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.ILoginService2;
import com.mymvc.repository.hibernate.service.resource.IUserTokenService2;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.basic.IDefaultControllerMethod;
import com.mymvc.system.bus.event.UserTokenEvent;
import com.mymvc.system.core.ResultResp;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.pojo.PredicatePojo;
import com.mymvc.system.utils.DateUtil;
import com.mymvc.system.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class LoginController extends BasicApiController implements IDefaultControllerMethod<User> {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ILoginService2 service2;

    @Autowired
    private IUserTokenService2 tokenService2;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @Override
    public ResultResp<Map<String, Object>> _doGet(HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        int page = 1;
        if (request.getParameter("page") != null){
            page = Integer.valueOf(request.getParameter("page"));
        }

        try {
            Map<String,Object> map = new HashMap<>();

            List<PredicatePojo> where = new ArrayList<>();
            map.put("userToken",tokenService2.getAll(page,where,"id",true));

            resp.setData(map);

        } catch (IllegalServiceException e) {
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @Override
    public ResultResp<Map<String, Object>> _doPost(User obj, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (password.length() < 32){
                password = Md5Util.md5(password);
            }
            User user = service2.doLogin(username,password);
            if (user != null){
                /**
                 * has login so send a message to tokenPublisher save the token object.
                 */
                UserToken token = new UserToken();
                token.setUserId(user.getId());
                token.setLat(0.0);
                token.setLnt(0.0);
                token.setLoginTime(DateUtil.getTime()/1000);
                token.setExpireIn(token.getLoginTime() + Constant.tokenExpireIn);
                token.setToken(user.getToken());
                token.setStatus(Status.valid);
                publisher.publishEvent(new UserTokenEvent(token));

                /**
                 * and save user login logs.
                 */
                UserLog userLog = new UserLog();
                userLog.setContent(getLang("info_login_success",true));
                userLog.setUserId(user.getId());
                userLog.setLogType(UserLogType.logined);
                this.saveUserLog(userLog);

                Map<String, Object> map = new HashMap<>();
                map.put("token",token.getToken());
                resp.setInfo("info_login_success");
                resp.setData(map);
            }

        }catch (IllegalServiceException e){
            e.printStackTrace();

            resp.setCode(e.getCode());
            resp.setInfo(e.getMessage());
        }
        return resp;
    }

    @Override
    public ResultResp<Map<String, Object>> _doPut(Integer id, User obj, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResultResp<Map<String, Object>> _doDelete(Integer id, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


}
