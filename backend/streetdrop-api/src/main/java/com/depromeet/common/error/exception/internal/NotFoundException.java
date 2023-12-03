package com.depromeet.common.error.exception.internal;

import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.Getter;

@Getter
public class NotFoundException extends BusinessException {

	public <T extends ErrorCodeInterface> NotFoundException(T errorCode) {
		super(errorCode.toErrorCode());
	}

	public <T extends ErrorCodeInterface> NotFoundException(T errorCode, String additionalMessage) {
		super(errorCode.toErrorCode(), additionalMessage);
	}

	public <T extends ErrorCodeInterface> NotFoundException(T errorCode, Long id) {
		super(errorCode.toErrorCode(), id + " is not found");
	}

}
