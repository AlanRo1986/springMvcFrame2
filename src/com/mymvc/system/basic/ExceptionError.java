package com.mymvc.system.basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by alan.luo on 2017/9/18.
 */
public abstract class ExceptionError extends Exception {

    private Integer code = 0;

    public static final Integer success = 1;
    public static final Integer error = 0;
    public static final Integer error_user_registered = 2;
    public static final Integer error_updated = 3;
    public static final Integer error_has_not_data = 4;
    public static final Integer error_userId_must_be_require = 5;
    public static final Integer error_has_not_username = 6;
    public static final Integer error_has_not_account = 7;
    public static final Integer error_user_password_invalid = 8;
    public static final Integer error_has_not_logined = 9;
    public static final Integer error_argument_invalid = 10;
    public static final Integer error_need_logined = 11;
    public static final Integer error_user_token_invalid = 12;
    public static final Integer error_inserted= 13;


    public static final Integer error_unauthorized_access = 401;
    public static final Integer error_unauthorized_access_2 = 4012;
    public static final Integer error_access_denied = 4013;


    private static final Map<Integer,String> errors = new HashMap<Integer,String>(){
        {
            put(success,"ok");
            put(error,"error");
            put(error_user_registered,"error_user_registered");
            put(error_updated,"error_updated");
            put(error_has_not_data,"error_has_not_data");
            put(error_userId_must_be_require,"error_userId_must_be_require");
            put(error_has_not_username,"error_has_not_username");
            put(error_has_not_account,"error_has_not_account");
            put(error_user_password_invalid,"error_user_password_invalid");
            put(error_has_not_logined,"error_has_not_logined");
            put(error_argument_invalid,"error_argument_invalid");
            put(error_need_logined,"error_need_logined");
            put(error_access_denied,"error_access_denied");
            put(error_unauthorized_access,"error_unauthorized_access");
            put(error_user_token_invalid,"error_user_token_invalid");
            put(error_inserted,"error_inserted");
            put(error_unauthorized_access_2,"error_unauthorized_access_else");
        }
    };

    public ExceptionError(String error){
        super(error);
    }

    public ExceptionError(Integer code){
        super(errors.get(code));
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
