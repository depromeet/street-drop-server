package com.depromeet.domains.user.dto.response;

import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserLevelResponseDto(
		@Schema(description = "사용자 ID", example = "1")
		Long userId,
		@Schema(description = "사용자 닉네임", example = "친절한 금자씨")
		String nickname,
		@Schema(description = "레벨명")
		String levelName,
		@Schema(description = "레벨 이미지")
		String levelImage,
		@Schema(description = "레벨 등급 설명")
		String levelDescription
) {
	public UserLevelResponseDto(User user) {
		this(
				user.getId(),
				user.getNickname(),
				user.getUserLevel().getName(),
				user.getUserLevel().getImage(),
				user.getUserLevel().getDescription()
		);
	}
}