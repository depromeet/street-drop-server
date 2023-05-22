package com.depromeet.streetdrop.domains.item.dto.response;

import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.itemLocation.dto.response.LocationResponseDto;
import com.depromeet.streetdrop.domains.music.dto.response.MusicResponseDto;
import com.depromeet.streetdrop.domains.user.dto.response.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ItemResponseDto(
		@Schema(description = "아이템 아이디", example = "1")
		Long itemId,

		@Schema(description = "사용자 정보")
		UserResponseDto user,

		@Schema(description = "사용자 위치", example = "성동구 성수1가 1동")
		LocationResponseDto location,

		@Schema(description = "사용자 코멘트", example = "이 노래 좋아요")
		String content,

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
				new LocationResponseDto(item.getItemLocation().getName()),
				item.getContent(),
				item.getCreatedAt()
		);
	}
}
