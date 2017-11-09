package com.mymvc.system.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by alan.luo on 2017/7/27.
 */
public class JsonUtil {
    public JsonUtil(){
    }

    public static <T> T JsonToObject(String json,Class<T> classz){
        Gson gson = new Gson();
        return gson.fromJson(json,classz);
    }

    public static Map<String,Object> JsonToMap(String json){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, Object> map = g.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
        return map;
    }

    public static Map<String,String> JsonToMaps(String json){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, String> map = g.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
        return map;
    }


    public static List<Map<String,Object>> JsonToList(String json){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        List<Map<String,Object>> map = g.fromJson(json, new TypeToken<List<Map<String, Object>>>() {}.getType());
        return map;
    }

    /**
     * 对象转换成json
     * @param object
     * @return
     */
    public static String ObjectToJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * 判断是否json
     * @param str
     * @return
     */
    public static boolean isJson(String str){
        String patten = "\\{\\\".*\\}$";
        return str.matches(patten);
    }


}
