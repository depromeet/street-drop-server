package com.depromeet.common.error.dto.interfaces;

/**
 * To handle error codes by domain, this interface must be extended and used.
 * Check ErrorCodeFieldInterface for fields and ErrorCodeCurtInterface for transducers.
 */
public non-sealed interface ErrorCodeInterface extends ErrorCodeCovertInterface, ErrorCodeFieldInterface {
}
