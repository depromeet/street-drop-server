package com.depromeet.common.error.dto.interfaces;

import org.springframework.http.HttpStatus;

/**
 * This interface provides a basic specification for defining domain error codes.
 * This interface used to extend errorCodeEnum.
 * Recommend to using Lombok Getter
 */
public sealed interface ErrorCodeFieldInterface permits ErrorCodeInterface {

    /**
     * Returns the HTTP status code.
     *
     * @return The HTTP status code.
     */
    HttpStatus getStatus();


    /**
     * Returns the error code. All errors are categorized by domain, The first start of any code is a domain code.  It is not duplicated by domain at all.
     *
     * @return The error code.
     */
    String getErrorResponseCode();


    /**
     * Returns the error title - Short Human Readable Error Title.
     *
     * @return The error title.
     */
    String getTitle();

    /**
     * Returns the error message - Human Readable Error Message, It is also recommended that you write solutions together.
     *
     * @return The error message.
     */
    String getMessage();

}
