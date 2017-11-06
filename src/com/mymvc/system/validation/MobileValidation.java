package com.mymvc.system.validation;

import com.mymvc.system.annotation.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by alan.luo on 2017/10/7.
 */
public class MobileValidation implements ConstraintValidator<Mobile,String> {

    private String regx = "^1[3|4|5|6|7|8]\\d{1}\\d{8}$";

    @Override
    public void initialize(Mobile mobile) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null){
            return false;
        }
        return s.matches(regx);
    }
}
