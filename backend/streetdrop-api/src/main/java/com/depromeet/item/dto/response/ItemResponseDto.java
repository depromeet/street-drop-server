package com.depromeet.item.dto.response;

import com.depromeet.item.Item;
import com.depromeet.user.dto.response.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ItemResponseDto(
		@Schema(description = "아이템 아이디", example = "1")
		Long itemId,

		@Schema(description = "사용자 정보")
        UserResponseDto user,

		@Schema(description = "사용자 위치", example = "성동구 성수1가 1동")
        com.depromeet.item.dto.response.ItemLocationResponseDto location,

		@Schema(description = "생성시간", example = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(
				shape = JsonFormat.Shape.STRING,
				pattern = "yyyy-MM-dd HH:mm:ss",
				locale = "Asia/Seoul"
		)
		LocalDateTime createdAt,

		@Schema(description = "아이템 좋아요 개수", example = "1")
		int itemLikeCount
) {
	public ItemResponseDto(Item item) {
		this(
				item.getId(),
				new UserResponseDto(item.getUser()),
				new ItemLocationResponseDto(item.getItemLocation().getName()),
				item.getCreatedAt(),
				item.getItemLikeCount()
		);
	}
}
