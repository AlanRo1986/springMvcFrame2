package com.mymvc.system.annotation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * Created by alan.luo on 2017/10/7.
 */
@Length(
        min = 6,max = 18,message = "error_validate_password"
)
@Constraint(
        validatedBy = {}
)
@NotNull(message = "error_validate_password")
@NotEmpty(message = "error_validate_password")
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    /**
     * locale message source.
     * The application.getLang("error_validate_mobile") will return like en_US|zh_CN|other message of country language.
     * @return
     */
    String message() default "error_validate_password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
