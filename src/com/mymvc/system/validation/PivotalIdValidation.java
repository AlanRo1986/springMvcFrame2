package com.mymvc.system.validation;

import com.mymvc.system.annotation.PivotalId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by alan.luo on 2017/11/3.
 */
public class PivotalIdValidation implements ConstraintValidator<PivotalId,Integer> {

    @Override
    public void initialize(PivotalId pivotalId) {

    }

    @Override
    public boolean isValid(Integer i, ConstraintValidatorContext constraintValidatorContext) {
        if (i == null){
            return false;
        }
        return i > 0;
    }
}
