package com.depromeet.external.swagger.annotation;

import com.depromeet.common.error.dto.ErrorCode;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Repeatable(ApiErrorResponses.class)
public @interface ApiErrorResponse {

    String description() default "";

    ErrorCode errorCode() default ErrorCode.INTERNAL_SERVER_ERROR;

}