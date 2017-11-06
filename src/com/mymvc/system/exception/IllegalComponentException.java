package com.mymvc.system.exception;

import com.mymvc.system.basic.ExceptionError;

/**
 * Created by alan.luo on 2017/9/18.
 */
public class IllegalComponentException extends ExceptionError {

    public IllegalComponentException(String error){
        super(error);
    }

    public IllegalComponentException(Integer code){
        super(code);
    }
}
