package com.depromeet.domains.user.dto.response;

import com.depromeet.user.UserLevel;
import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserLevelResponseDto(
		@Schema(description = "사용자 ID", example = "1")
		Long userId,
		@Schema(description = "사용자 닉네임", example = "친절한 금자씨")
		String nickname,
		@Schema(description = "사용자 프로필 이미지 URL")
		String profileImage,
		@Schema(description = "레벨 랭킹")
		int levelRank,
		@Schema(description = "레벨명")
		String levelName,
		@Schema(description = "레벨 이미지")
		String levelImage,
		@Schema(description = "레벨 등급 설명")
		String levelDescription
) {
	public UserLevelResponseDto(User user, UserLevel userLevel) {
		this(
				user.getId(),
				user.getNickname(),
				"https://s3.orbi.kr/data/file/united/35546557a06831597f6e7851cb6c86e9.jpg",
				userLevel.getLevel().getLevelRank(),
				userLevel.getLevel().getLevelName(),
				userLevel.getLevelImage(),
				userLevel.getLevel().getLevelDescription()
		);
	}
}