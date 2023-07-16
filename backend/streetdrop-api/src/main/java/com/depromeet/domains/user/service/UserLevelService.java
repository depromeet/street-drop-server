package com.depromeet.domains.user.service;

import com.depromeet.domains.user.dto.response.UserLevelResponseDto;
import com.depromeet.domains.user.event.UserLevelCreateEvent;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserLevelService {

	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public UserLevelResponseDto getUserLevel(User user) {
		var userLevel = user.getUserLevel();
		if (userLevel != null) {
			return new UserLevelResponseDto(user);
		} else {
			eventPublisher.publishEvent(new UserLevelCreateEvent(this, user));
		}
		return new UserLevelResponseDto(user);
	}
}

