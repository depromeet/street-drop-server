package com.depromeet.common.error.exception.common;

import com.depromeet.common.error.dto.ErrorCode;

public class InvalidUserException extends BusinessException {
	public InvalidUserException(ErrorCode errorCode, String content) {
		super(errorCode, content + " is invalid.");
	}
}
