package com.depromeet.streetdrop.domains.aws.dto.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum S3ImageCategory {
	USER_PROFILE("사용자 프로필 이미지");

	private final String description;
}
