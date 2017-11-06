package com.mymvc.service.resource;

import com.mymvc.system.exception.IllegalServiceException;

import java.util.Map;

/**
 * Created by alan.luo on 2017/9/18.
 */
public interface ITestService {

    String getName() throws IllegalServiceException;

    void setName(String name);
}
