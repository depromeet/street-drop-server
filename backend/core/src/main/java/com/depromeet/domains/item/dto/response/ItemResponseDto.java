package com.depromeet.domains.item.dto.response;

import com.depromeet.domains.item.entity.Item;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ItemResponseDto(
		@Schema(description = "아이템 아이디", example = "1")
		Long itemId,

		@Schema(description = "사용자 정보")
        UserResponseDto user,

		@Schema(description = "사용자 위치", example = "성동구 성수1가 1동")
		ItemLocationResponseDto location,

		@Schema(description = "생성시간", example = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(
				shape = JsonFormat.Shape.STRING,
				pattern = "yyyy-MM-dd HH:mm:ss",
				locale = "Asia/Seoul"
		)
		LocalDateTime createdAt
) {
	public ItemResponseDto(Item item) {
		this(
				item.getId(),
				new UserResponseDto(item.getUser()),
				new ItemLocationResponseDto(item.getItemLocation().getName()),
				item.getCreatedAt()
		);
	}
}
