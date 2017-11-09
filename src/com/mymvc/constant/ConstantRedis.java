package com.mymvc.constant;

/**
 * Created by alan.luo on 2017/11/6.
 */
public class ConstantRedis {
    public static final String IP = "127.0.0.1";
    public static final int PORT = 6379;
    public static final String PASSWORD = "";

    public static final int maxIdle = 8;
    public static final int maxTotal = 8;
    public static final int maxWaitMillis = -1;
    public static final boolean testOnBorrow = true;

    public static final int corePoolSize = 10;
    public static final int maxPoolSize = 100;
    public static final int queueCapacity = 30;
    public static final int keepAliveSeconds = 100;
    public static final String threadNamePrefix = "redisPool";

    public static final String CHANNEL = "message&socket";

}
