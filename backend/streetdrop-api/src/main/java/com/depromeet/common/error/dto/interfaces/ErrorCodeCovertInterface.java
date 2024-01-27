package com.depromeet.common.error.dto.interfaces;

public sealed interface ErrorCodeCovertInterface permits ErrorCodeInterface {

    /**
     * Converts this to an ErrorCode object.
     *
     * @return An ErrorCode object.
     */
    ErrorCode toErrorCode();
}
