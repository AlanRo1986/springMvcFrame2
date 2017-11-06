package com.mymvc.app.controller;

import com.mymvc.repository.hibernate.basic.UserLogType;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.service.resource.ILoginService2;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.basic.IDefaultControllerMethod;
import com.mymvc.system.core.ResultResp;
import com.mymvc.system.exception.IllegalServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by alan.luo on 2017/9/20.
 */
@RestController
public class LoginOutController extends BasicApiController implements IDefaultControllerMethod<User> {

    @Autowired
    private ILoginService2 service2;

    @Override
    public ResultResp<Map<String, Object>> _doGet(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

    @RequestMapping(value = "/loginOut",method = RequestMethod.POST)
    public ResultResp<Map<String, Object>> _doPost(User user, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {

            if(service2.doLoginOut(getToken())){
                UserLog userLog = new UserLog();
                userLog.setContent(getLang("info_loginOut_success",true));
                userLog.setLogType(UserLogType.logOut);
                this.saveUserLog(userLog);

                resp.setInfo("info_loginOut_success");
            }

        }catch (IllegalServiceException e){
            e.printStackTrace();
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
