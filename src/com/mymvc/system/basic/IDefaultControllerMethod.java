package com.mymvc.system.basic;

import com.mymvc.system.core.ResultResp;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by alan.luo on 2017/9/18.
 */
public interface IDefaultControllerMethod<T> {

    ResultResp<Map<String, Object>> _doGet(HttpServletRequest request, HttpServletResponse response);

    ResultResp<Map<String, Object>> _doPost(T obj,HttpServletRequest request, HttpServletResponse response);

    ResultResp<Map<String, Object>> _doPut(Integer id,T obj,HttpServletRequest request, HttpServletResponse response);

    ResultResp<Map<String, Object>> _doDelete(Integer id, HttpServletRequest request, HttpServletResponse response);
}
