package com.streetdrop.domains.item.dto.response;
import com.streetdrop.domains.user.dto.response.UserResponseDto;
import com.streetdrop.item.ItemLike;
import com.streetdrop.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record ItemLikeResponseDto (
		@Schema(description = "아이템 좋아요 아이디",example = "1")
		Long itemLikeId,

		@Schema(description = "사용자 정보")
        UserResponseDto user,

		@Schema(description = "아이템 좋아요 개수")
		int itemLikeCount
) {
	public ItemLikeResponseDto(User user, ItemLike itemLike) {
		this(
				itemLike.getId(),
				new UserResponseDto(user),
				itemLike.getItem().getItemLikeCount()
		);
	}
}
