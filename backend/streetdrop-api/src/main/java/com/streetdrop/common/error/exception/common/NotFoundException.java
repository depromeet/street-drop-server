package com.streetdrop.common.error.exception.common;

import com.streetdrop.common.error.dto.ErrorCode;

public class NotFoundException extends BusinessException {
	public NotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

	public NotFoundException(ErrorCode errorCode, String content) {
		super(errorCode, content + " is not found");
	}

	public NotFoundException(ErrorCode errorCode, Long id) {
		super(errorCode, id + " is not found");
	}
}
