package com.depromeet.domains.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserItemDto {
	private Long userId;
	private Integer itemCount;

	public UserItemDto(Long userId, Integer itemCount) {
		this.userId = userId;
		this.itemCount = itemCount;
	}
}
