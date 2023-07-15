package com.depromeet.domains.user.handler;

import com.depromeet.domains.user.event.UserLevelCreateEvent;
import com.depromeet.domains.user.repository.UserLevelRepository;
import com.depromeet.user.User;
import com.depromeet.user.UserLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserLevelCreateEventHandler {

	public static final String NAME = "L.1 드랍 스타터";
	public static final String DESCRIPTION = "당신은 드랍 스타터입니다. 드랍을 시작해보세요!";
	public static final String IMAGE_URL = "https://drop-the-code.s3.ap-northeast-2.amazonaws.com/USER_PROFILE/Level1.png";

	private final UserLevelRepository userLevelRepository;

	@EventListener
	public void createLevel(UserLevelCreateEvent event) {
		var user = event.getUser();
		var newLevel = createLevel(user, IMAGE_URL);
		user.changeLevel(newLevel);
		log.info("create user level: {}, {}", user.getNickname(), newLevel.getName());
		userLevelRepository.save(newLevel);
	}

	private UserLevel createLevel(User user, String IMAGE_URL) {
		return UserLevel.builder()
				.name(NAME)
				.description(DESCRIPTION)
				.image(IMAGE_URL)
				.users(Collections.singletonList(user))
				.build();
	}

}
