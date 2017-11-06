package com.mymvc.system.exception;

import com.mymvc.system.basic.ExceptionError;

/**
 * Created by alan.luo on 2017/9/18.
 */
public class IllegalHttpException extends ExceptionError {

    public IllegalHttpException(String error){
        super(error);
    }

    public IllegalHttpException(Integer code){
        super(code);
    }
}
