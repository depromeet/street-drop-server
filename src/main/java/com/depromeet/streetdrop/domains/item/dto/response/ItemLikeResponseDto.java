package com.depromeet.streetdrop.domains.item.dto.response;

import com.depromeet.streetdrop.domains.item.entity.ItemLike;
import com.depromeet.streetdrop.domains.user.dto.response.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ItemLikeResponseDto (
		@Schema(description = "아이템 좋아요 아이디",example = "1")
		Long itemLikeId,

		@Schema(description = "사용자 정보")
		UserResponseDto user,

		@Schema(description = "아이템 정보")
		ItemResponseDto item,

		@Schema(description = "생성시간", example = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(
				shape = JsonFormat.Shape.STRING,
				pattern = "yyyy-MM-dd HH:mm:ss",
				locale = "Asia/Seoul"
		)
		LocalDateTime createdAt
) {
	public ItemLikeResponseDto(ItemLike itemLike) {
		this(
				itemLike.getId(),
				new UserResponseDto(itemLike.getUser()),
				new ItemResponseDto(itemLike.getItem()),
				itemLike.getCreatedAt()
		);
	}
}
