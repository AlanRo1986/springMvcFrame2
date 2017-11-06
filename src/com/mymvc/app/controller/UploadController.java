package com.mymvc.app.controller;

import com.mymvc.model.UploadModel;
import com.mymvc.service.impl.UploadServiceImpl;
import com.mymvc.system.basic.BasicApiController;
import com.mymvc.system.basic.IDefaultControllerMethod;
import com.mymvc.system.callback.IFilePutCallback;
import com.mymvc.system.core.ResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alan.luo on 2017/9/21.
 */

@RestController
public class UploadController extends BasicApiController{

    @Autowired
    private UploadServiceImpl uploadService;


    @RequestMapping(value = "/upload/base64",method = RequestMethod.POST)
    @ResponseBody
    public ResultResp<Map<String, Object>> _doPost(HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();
        Map<String, Object> map = new HashMap<>();

        if (request.getParameter("file") != null){
            String file = request.getParameter("file");
            uploadService.doSave(file, new IFilePutCallback() {
                @Override
                public void success(int code, String info, UploadModel model) {
                    resp.setCode(code);
                    resp.setInfo(info);
                    map.put("file",model);
                }

                @Override
                public void error(int code, String info) {
                    resp.setCode(code);
                    resp.setInfo(info);
                }
            });
        }

        resp.setData(map);

        return resp;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public ResultResp<Map<String, Object>> _doPost(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        ResultResp<Map<String, Object>> resp = new ResultResp<>();
        Map<String, Object> map = new HashMap<>();

        uploadService.doSave(file, new IFilePutCallback() {
            @Override
            public void success(int code, String info, UploadModel model) {
                resp.setCode(code);
                resp.setInfo(info);
                map.put("file",model);
            }

            @Override
            public void error(int code, String info) {
                resp.setCode(code);
                resp.setInfo(info);
            }
        });
        resp.setData(map);

        return resp;
    }


}
