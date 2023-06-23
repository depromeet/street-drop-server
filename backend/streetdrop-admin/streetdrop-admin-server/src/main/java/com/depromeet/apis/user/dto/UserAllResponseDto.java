package com.depromeet.apis.user.dto;

import com.depromeet.user.User;

import java.util.List;

public record UserAllResponseDto(
		List<UserResponseDto> users
) {
	public record UserResponseDto(
			String nickname,
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
