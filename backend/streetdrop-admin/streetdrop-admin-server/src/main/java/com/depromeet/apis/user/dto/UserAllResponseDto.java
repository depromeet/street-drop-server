package com.depromeet.apis.user.dto;

import com.depromeet.user.User;

import java.util.List;

public record UserAllResponseDto(
		List<UserResponseDto> users
) {
	public record UserResponseDto(
			Long id,
			String nickname,
			String idfv
	) {
		public UserResponseDto(User user) {
			this(
					user.getId(),
					user.getNickname(),
					user.getIdfv()
			);
		}
	}
}
