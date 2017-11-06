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
public class ScheduleProvider extends BasicProvider {

    public ScheduleProvider() {

    }

    /**
     * "0 0 12 * * ?" 每天中午12点触发
     */
    //@Scheduled(cron = "0 0 23 * * ?")//每天23点执行
    //@Scheduled(fixedDelay = 15_000L, initialDelay = 10_000L)//每10秒执行一次，延迟15秒开始
    public void doRun() {
        TestServiceImpl testService =
                (TestServiceImpl) Application.getInstance(null).getBean(TestServiceImpl.class);

        try {
            println("------schedule-------");
            println("Scheduled:"+System.currentTimeMillis());
            this.doAsync();
            println("TestProvider->call：testService.getName(" + testService.getName() + ")");
            println("------end-------");
        } catch (IllegalServiceException e) {
            e.printStackTrace();
        }

    }

    //@Async
    public void doAsync() {
        println("------doAsync-------");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("doAsync:"+System.currentTimeMillis());
        println("------end-------");

    }
}
