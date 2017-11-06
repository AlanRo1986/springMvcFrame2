package com.mymvc.system.provider;

import com.mymvc.system.annotation.Provider;
import com.mymvc.system.basic.BasicProvider;
import com.mymvc.system.core.Application;
import com.mymvc.system.exception.IllegalValidateException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * The form validate class.
 * example:
 *      try{
 *          ValidatorProvider<UserModel> validator = new ValidatorProvider<UserModel>();
 *          validator.validate(object);
 *      }cache(IllegalValidateException e){
 *          e.printStackTrace();
 *          e.getMessage();//this is validate message.
 *          e.getCode();
 *      }
 * Created by alan.luo on 2017/10/07.
 */
@Provider
public class ValidatorProvider<T> extends BasicProvider {

    public ValidatorProvider() {

    }

    public void validate(T obj) throws IllegalValidateException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (violations.size() > 0) {
            for (ConstraintViolation<T> violation : violations) {
                throw new IllegalValidateException(Application.getInstance(null).getLang(violation.getMessage(),"errors"));
            }
        }
    }
}
