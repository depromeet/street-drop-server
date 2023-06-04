package com.depromeet.common.annotation;

import com.depromeet.common.validator.LatitudeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = LatitudeValidator.class)
public @interface IsLatitude {

    String message() default "Not valid latitude, must be between -90 and 90";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}