package com.streetdrop.domains.user.event;

import com.streetdrop.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLevelSetEvent {
	private User user;
	private Long userLevelId;
}
