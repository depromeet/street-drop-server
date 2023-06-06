package com.depromeet.streetdrop.domains.user.dto.response;

import com.depromeet.streetdrop.domains.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record UserAllResponseDto(
		List<UserResponseDto> users
) {
	public record UserResponseDto(
			@Schema(description = "사용자 닉네임", example = "사용자 1")
			String nickname,
			@Schema(description = "IDFV 값", example = "12345")
			String idfv
	) {
		public UserResponseDto(User user) {
			this(
					user.getNickname(),
					user.getIdfv()
			);
		}
	}
}
