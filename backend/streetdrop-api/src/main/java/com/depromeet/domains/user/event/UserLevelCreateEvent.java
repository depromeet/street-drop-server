package com.depromeet.domains.user.event;

import com.depromeet.user.User;
import org.springframework.context.ApplicationEvent;

public class UserLevelCreateEvent extends ApplicationEvent {

	private final User user;

	public UserLevelCreateEvent(Object source, User user) {
		super(source);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
