package com.depromeet.domains.user.handler;

import com.depromeet.domains.user.event.UserLevelCreateEvent;
import com.depromeet.domains.user.repository.UserLevelRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.user.User;
import com.depromeet.user.UserLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserLevelCreateEventHandler {

	public static final String NAME = "L.1 드랍 스타터";
	public static final String DESCRIPTION = "당신은 드랍 스타터입니다. 드랍을 시작해보세요!";
	public static final String FILE_NAME = "Level1.png";

	@Value("${cloud.aws.s3.dir}")
	private String bucketName;

	private final UserLevelRepository userLevelRepository;
	private final UserRepository userRepository;

	@EventListener
	public void createLevel(UserLevelCreateEvent event) {
		var user = event.getUser();
		var newLevel = createLevel(user);
		userLevelRepository.save(newLevel);
		user.changeLevel(newLevel);
		userRepository.save(user);
	}

	private UserLevel createLevel(User user) {
		return UserLevel.builder()
				.name(NAME)
				.description(DESCRIPTION)
				.image(bucketName + FILE_NAME)
				.users(Collections.singletonList(user))
				.build();
	}

}
