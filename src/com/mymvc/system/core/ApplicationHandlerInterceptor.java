package com.mymvc.system.core;

import com.mymvc.system.exception.IllegalAccessDeniedException;
import com.mymvc.system.provider.SecurityProvider;
import com.mymvc.system.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * user access role check.
 * Created by alan.luo on 2017/10/6.
 */
public class ApplicationHandlerInterceptor  implements HandlerInterceptor {

    @Autowired
    private SecurityProvider security;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println("Locale>>"+httpServletRequest.getLocale().getCountry()+"_"+httpServletRequest.getLocale().getLanguage());
        try {
           return security.intersection();
        }catch (IllegalAccessDeniedException e){
            e.printStackTrace();

            ResultResp<Void> resp = new ResultResp<>();
            resp.setCode(e.getCode());
            resp.setInfo(e.getMessage());
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("application/json");
            //httpServletResponse.setStatus(e.getCode());
            httpServletResponse.getWriter().append(JsonUtil.ObjectToJson(resp));
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {


    }
}
