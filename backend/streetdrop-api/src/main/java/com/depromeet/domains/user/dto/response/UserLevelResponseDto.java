package com.depromeet.domains.user.dto.response;

import com.depromeet.user.User;
import com.depromeet.user.UserLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
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
	public UserLevelResponseDto(User user, UserLevel level) {
		this(
				user.getId(),
				user.getNickname(),
				level.getName(),
				level.getImage(),
				level.getDescription()
		);
	}
}