package com.depromeet.external.swagger.annotation;

import io.swagger.v3.oas.annotations.extensions.Extension;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiErrorResponses {

    ApiErrorResponse[] value() default {};

    Extension[] extensions() default {};

}