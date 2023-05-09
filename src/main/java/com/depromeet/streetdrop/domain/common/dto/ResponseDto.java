package com.depromeet.streetdrop.domain.common.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public record ResponseDto<T>(T date) implements Serializable {
	public static <T> ResponseEntity<T> ok(T data) {
		return ResponseEntity.ok(data);
	}

	public static <T> ResponseEntity<T> created(T data) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(data);
	}

	public static ResponseEntity<Void> noContent() {
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
}
