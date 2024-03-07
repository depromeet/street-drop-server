package com.depromeet.common.error;

import org.springframework.http.HttpStatus;

/**
 * This interface provides a basic specification for defining domain error codes.
 *
 *
 * This interface used to extend errorCodeEnum.
 */
public interface ErrorCodeInterface {

    /**
     * Returns the HTTP status code.
     *
     * @return The HTTP status code.
     */
    HttpStatus getStatus();


    /**
     * Returns the error code. It is not duplicated by domain at all.
     *
     * @return The error code.
     */
    String getCode();

    /**
     * Returns the error domain code. All errors are categorized by domain, so check the ErrorDomain code to see them.
     *
     * @return The domain code.
     */
    ErrorDomain getDomainCode();

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

    /**
     * Converts this to an ErrorCode object.
     *
     * @return An ErrorCode object.
     */
    ErrorCode toErrorCode();

}
