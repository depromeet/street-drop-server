package com.depromeet.common.error.exception.internal;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	private final ErrorCode errorCode;

	public <T extends ErrorCodeInterface> BusinessException(T errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode.toErrorCode();
	}

	public <T extends ErrorCodeInterface> BusinessException(T errorCode, String additionalMessage) {
		this.errorCode = errorCode.toErrorCode();
		this.errorCode.appendMessage(additionalMessage);
	}
}
