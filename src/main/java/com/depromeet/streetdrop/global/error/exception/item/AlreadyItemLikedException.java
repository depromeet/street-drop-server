package com.depromeet.streetdrop.global.error.exception.item;

import com.depromeet.streetdrop.global.error.dto.ErrorCode;
import com.depromeet.streetdrop.global.error.exception.common.BusinessException;


public class AlreadyItemLikedException extends BusinessException {
	public AlreadyItemLikedException() {
		super(ErrorCode.ALREADY_ITEM_LIKED_ERROR);
	}
}
