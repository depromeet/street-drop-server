package com.depromeet.domains.user.event;

import com.depromeet.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@AllArgsConstructor
@Getter
public class UserLevelCreateEvent {
	private User user;
}
