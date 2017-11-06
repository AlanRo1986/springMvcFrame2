package com.mymvc.system.annotation;

import com.mymvc.system.validation.ArticleContentValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by alan.luo on 2017/10/7.
 */

/**
 * custom constraint class.
 */
@Constraint(
        validatedBy = {ArticleContentValidation.class}
)
@Documented
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArticleContent {

    /**
     * locale message source.
     * The application.getLang("error_validate_article_content") will return like en_US|zh_CN|other message of country language.
     * @return
     */
    String message() default "error_validate_article_content";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

