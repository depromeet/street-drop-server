package com.streetdrop.domains.user.dto.response;

import com.streetdrop.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record BlockUserResponseDto(
		@Schema(description = "차단된 사용자 ID", example = "1")
		Long userId,
		@Schema(description = "차단된 사용자 닉네임", example = "사용자 1")
		String nickname
) {
	public BlockUserResponseDto(User user) {
		this(
				user.getId(),
				user.getNickname()
		);
	}
}
