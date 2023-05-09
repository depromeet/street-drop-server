package com.depromeet.streetdrop.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public record PageResponseDto<T>(
		List<T> data,
		int page,
		int size,
		int totalPage,
		boolean firstPage,
		boolean lastPage
) implements Serializable {
	private PageResponseDto(final Page<T> page) {
		this(
				page.getContent(),
				page.getNumber(),
				page.getSize(),
				page.getTotalPages(),
				page.isFirst(),
				page.isLast()
		);
	}

	public static <T> PageResponseDto<T> of(final Page<T> page) {
		return new PageResponseDto<>(page);
	}

	public static <T>ResponseEntity<PageResponseDto<T>> ok(final Page<T> page) {
		return ResponseEntity.ok(new PageResponseDto<>(page));
	}
}
