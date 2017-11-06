package com.mymvc.system.exception;

import com.mymvc.system.basic.ExceptionError;

/**
 * Created by alan.luo on 2017/9/18.
 */
public class IllegalAccessDeniedException extends ExceptionError {

    public IllegalAccessDeniedException(String error) {
        super(error);
        setCode(401);
    }

    public IllegalAccessDeniedException(Integer code) {
        super(code);
    }
}
