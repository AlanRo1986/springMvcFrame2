package com.mymvc.app.controller;

import com.mymvc.service.resource.ITestService;
import com.mymvc.system.basic.IDefaultControllerMethod;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.core.Application;
import com.mymvc.system.core.ResultResp;

import com.mymvc.system.listener.RequestListener;
import com.mymvc.system.provider.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by alan.luo on 2017/9/18.
 */

@RestController
public class TestController extends BasicApiController   {

    @Autowired
    private ITestService testService;

    @Autowired
    private SessionProvider sessionProvider;

    public TestController() {
        super(TestController.class);
    }


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public ResultResp<Map<String, Object>> _doGet1(HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        Application.getInstance(null).catBeans();

        RequestListener listener = RequestListener.getInstance();
        logger(listener.getController()+">>>"+
        listener.getAction());


        return resp;
    }

    @RequestMapping(value = "/test3",method = RequestMethod.GET)
    @ResponseBody
    public ResultResp<Map<String, Object>> _doPut(HttpServletRequest request, HttpServletResponse response) {
        ResultResp<Map<String, Object>> resp = new ResultResp<>();


        return resp;
    }


}
