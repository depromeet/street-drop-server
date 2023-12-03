package com.depromeet.external.swagger.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Repeatable(ApiErrorResponses.class)
public @interface ApiErrorResponse {

    String description() default "";

    String errorCode() default "COMMON_INTERNAL_SERVER_ERROR";

}