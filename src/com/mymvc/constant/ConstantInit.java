package com.mymvc.constant;

/**
 * Created by alan.luo on 2017/9/21.
 */
public class ConstantInit {
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT&useSSL=false&characterEncoding=utf8";
    public static final String username = "root";
    public static final String password = "root";
    public static final int initialSize = 0;
    public static final int maxActive = 1000;
    public static final int maxIdle = 100;
    public static final int maxOpen = 100;

    public static final String validationQuery = "select 1";
    public static final boolean testWhileIdle = true;
    public static final int timeBetweenEvictionRunsMillis = 3600000;
    public static final long minEvictableIdleTimeMillis = 18000000;
    public static final boolean testOnBorrow = true;

    public static final int PAGE_SIZE = 20;
}
