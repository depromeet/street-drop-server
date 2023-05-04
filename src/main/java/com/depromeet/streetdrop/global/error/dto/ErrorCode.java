package com.depromeet.streetdrop.global.error.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	/*
	 * 400 BAD_REQUEST: 잘못된 요청
	 */
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "C-0000", "Bad Request"),

	/*
	 * 404 NOT_FOUND: 리소스를 찾을 수 없음
	 */
	NOT_FOUND(HttpStatus.NOT_FOUND, "C-0001", "Not Found the Contents"),

	/*
	 * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
	 */
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C-0002", "Method Not Allowed"),

	/*
	 * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-0003", "Internal Server Error");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
