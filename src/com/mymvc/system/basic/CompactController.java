package com.mymvc.system.basic;


import com.mymvc.constant.Constant;
import com.mymvc.system.core.Application;
import com.mymvc.system.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created by alan.luo on 2017/9/18.
 */
@RestController
public abstract class CompactController {

    private Logger logger;

    @Autowired
    private HttpServletRequest request;

    public CompactController(Class classz){
        logger = Logger.getLogger(classz.getName());
    }

    public void logger(Object o){
        logger.info(o.toString());
    }

    public void println(Object o){
        if (o == null){
            System.out.println("CompactController>>null");
        }else{
            System.out.println("CompactController>>"+o.toString());
        }
    }

    /**
     * 本地化语言
     * @param name
     * @param isInfo
     * @return
     */
    public String getLang(String name,boolean isInfo){
        return Application.getInstance(null).getLang(name,isInfo ? Constant.langTypeInfo : Constant.langTypeErrors);
    }

    /**
     * 返回一个token
     * @return
     */
    public String getToken(){
        return request.getHeader(Constant.tokenKey);
    }

    /**
     * 返回客户端的IP地址
     * @return
     */
    public String getClientIp(){
        return CommonUtil.getClientIp(request);
    }

}
