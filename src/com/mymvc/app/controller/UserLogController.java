package com.mymvc.app.controller;

import com.mymvc.repository.hibernate.basic.Criteria;
import com.mymvc.repository.hibernate.basic.Delete;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.basic.UserLogType;
import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.repository.hibernate.pojo.UserLog;
import com.mymvc.repository.hibernate.service.resource.IUserLogService2;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.basic.ExceptionError;
import com.mymvc.system.basic.IDefaultControllerMethod;
import com.mymvc.system.core.ResultResp;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.exception.IllegalValidateException;
import com.mymvc.system.pojo.PredicatePojo;
import com.mymvc.system.utils.CommonUtil;
import com.mymvc.system.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
 * Created by alan.luo on 2017/11/2.
 */
@RestController
public class UserLogController extends BasicApiController implements IDefaultControllerMethod<UserLog> {

    @Autowired
    private IUserLogService2 service2;

    @RequestMapping(value = "/userLog",method = RequestMethod.GET)
    @Override
    public ResultResp<Map<String, Object>> _doGet(HttpServletRequest request, HttpServletResponse response) {
        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        String  id = request.getParameter("id");
        String  userId = request.getParameter("userId");
        String  keywords = request.getParameter("keywords");

        int page = 1;
        if (request.getParameter("page") != null){
            page = Integer.valueOf(request.getParameter("page"));
        }

        try {
            Map<String,Object> map = new HashMap<>();
            if (id == null){
                if (StringUtils.isEmpty(userId)){
                    throw new IllegalValidateException(ExceptionError.error_userId_must_be_require);
                }

                List<PredicatePojo> where = new ArrayList<>();
                if (keywords != null){

                    keywords = "%" + keywords + "%";
                    List<PredicatePojo> like = new ArrayList<>();
                    like.add(new PredicatePojo("content",keywords, Criteria.like));

                    PredicatePojo pojo = new PredicatePojo();
                    pojo.setLikeObj(like);
                    pojo.setCriteria(Criteria.like);
                    where.add(pojo);
                }

                where.add(new PredicatePojo("userId",userId,Criteria.equal));
                map.put("userLog",service2.getAll(page,where,"id",true));
            }else{
                UserLog userLog = service2.getById(Integer.parseInt(id));
                map.put("userLog",userLog);
            }

            resp.setData(map);

        } catch (IllegalServiceException|IllegalValidateException e) {
            e.printStackTrace();

            resp.setInfo(e.getMessage());
            resp.setCode(e.getCode());
        }

        return resp;
    }

    @RequestMapping(value = "/userLog",method = RequestMethod.POST)
    @Override
    public ResultResp<Map<String, Object>> _doPost(UserLog obj, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();
        try {
            UserLog userLog = new UserLog();
            userLog.setUserId(94);
            userLog.setContent(getLang("info_user_registered",true));
            userLog.setLogType(UserLogType.registered);
            userLog.setIpAddr(CommonUtil.getClientIp(request));
            userLog.setCreateTime(DateUtil.getTime()/1000);
            userLog.setStatus(Status.valid);
            userLog.setIsDelete(Delete.normal);

            service2.save(userLog);
        } catch (IllegalServiceException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public ResultResp<Map<String, Object>> _doPut(Integer id, UserLog obj, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @RequestMapping(value = "/userLog",method = RequestMethod.DELETE)
    @Override
    public ResultResp<Map<String, Object>> _doDelete(Integer id, HttpServletRequest request, HttpServletResponse response) {
        ResultResp<Map<String, Object>> resp = new ResultResp<>();

        try {
            if (id == null){
                throw new IllegalValidateException("error_validate_id");
            }

            if (service2.remove(Integer.valueOf(id)) > 0){
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
