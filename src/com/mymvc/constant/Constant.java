package com.mymvc.constant;

/**
 * Created by alan.luo on 2017/9/21.
 */
public class Constant {

    /**
     * upload config
     */
    public static final String uploadDirectory = "D:/upload/";
    public static final String[] uploadAllowFiles = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

    /**
     * locale config
     */
    public static final String defaultLocale = "zh_CN";

    public static final String basePackages = "com.mymvc";

    public static final String hashKey = "dsgdsfgsfd";
    //header key.
    public static final String version = "version";

    public static final String tokenKey = "token";
    public static final Long tokenExpireIn = 3600L*24L*60L;

    /**
     * 本地化语言
     */
    public static final String langTypeInfo = "lang";
    public static final String langTypeErrors = "errors";


}
