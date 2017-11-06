package com.mymvc.system.annotation;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

/**
 * Created by alan.luo on 2017/10/7.
 */


@Max(value = 190,message = "error_validate_age")
@Min(value = 16,message = "error_validate_age")
@Constraint(
        validatedBy = {}
)
@Documented
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {
    String message() default "error_validate_age";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

