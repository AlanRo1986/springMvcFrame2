package com.mymvc.system.validation;

import com.mymvc.system.annotation.ArticleContent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by alan.luo on 2017/11/3.
 */
public class ArticleContentValidation implements ConstraintValidator<ArticleContent, String> {

    @Override
    public void initialize(ArticleContent articleContent) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null){
            return false;
        }
        return s.length() >= 10 && s.length() < 100000;
    }
}
