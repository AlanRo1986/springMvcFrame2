package com.mymvc.app.controller;


import com.mymvc.system.basic.IDefaultControllerMethod;
import com.mymvc.system.core.ResultResp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by alan.luo on 2017/9/18.
 */

@Controller
public class Test2Controller  {

    public Test2Controller() {

    }


    @RequestMapping(value = "/test2/{id}",method = RequestMethod.GET)
    public ModelAndView _doPost2(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) {
        ModelAndView view = new ModelAndView();
        view.addObject("id",id);
        view.setViewName("Home/index");
        return view;
    }


}
