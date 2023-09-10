package com.depromeet.common.annotation;
import com.depromeet.common.annotation.validator.BannedWordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = BannedWordValidator.class)
public @interface NotBannedWord {

    String message() default "Cannot use this banned word";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}