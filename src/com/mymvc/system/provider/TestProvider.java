package com.mymvc.system.provider;

import com.mymvc.service.impl.TestServiceImpl;
import com.mymvc.system.basic.BasicProvider;
import com.mymvc.system.core.Application;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.annotation.Provider;

/**
 * Created by alan.luo on 2017/9/19.
 */
@Provider
public class TestProvider extends BasicProvider {

    public TestProvider() {

    }

    public void doRun() {
        TestServiceImpl testService =
                (TestServiceImpl) Application.getInstance(null).getBean(TestServiceImpl.class);

        try {
            println("TestProvider->call：testService.getName(" + testService.getName() + ")");
        } catch (IllegalServiceException e) {
            e.printStackTrace();
        }

    }

}
