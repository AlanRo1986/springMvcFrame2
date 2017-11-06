package com.mymvc.system.provider;


import com.mymvc.system.provider.basic.HttpRequestData;
import com.mymvc.system.provider.handler.HttpConnector;
import com.mymvc.system.annotation.Provider;
import com.mymvc.system.utils.JsonUtil;

import java.util.Map;

/**
 * Created by alan.luo on 2017/7/27.
 */
@Provider
public class HttpProvider extends HttpConnector {

    public HttpProvider(){
        super();
    }

    public static HttpProvider getInstance(){
        return new HttpProvider();
    }

    /**
     * get
     * @param url
     * @param data
     * @return
     */
    public Map<String,Object> get(String url,HttpRequestData data){
        data.setMethod(GET);

        Map<String,Object> map =this.go(url,data);
        logger(map.toString());
        return  map;
    }


    /**
     * post
     * @param url
     * @param data
     * @return
     */
    public Map<String,Object> post(String url,HttpRequestData data){

        data.setMethod(POST);

        Map<String,Object> map =this.go(url,data);
        logger(map.toString());

        return  map;
    }

    /**
     *
     * @param url
     * @param data
     * @return
     */
    private final Map<String,Object> go(String url,HttpRequestData data){
        Map<String,Object> map = this.curl(url,data);
        map.put(RESPONSE_DATA, JsonUtil.JsonToMap((String) map.get(RESPONSE_DATA)));

        return map;
    }

}
