package com.depromeet.domains.user.event;

import com.depromeet.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLevelSetEvent {
	private User user;
	private Long userLevelId;
}
