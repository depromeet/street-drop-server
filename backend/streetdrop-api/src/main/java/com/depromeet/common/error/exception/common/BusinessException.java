package com.depromeet.common.error.exception.common;

import com.depromeet.common.error.dto.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorCode errorCode, Long id) {
		super(id + " is not found");
		this.errorCode = errorCode;
	}
}
