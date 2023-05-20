package com.depromeet.streetdrop.domains.user.service;

import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
	private  final UserRepository userRepository;

	@Transactional(readOnly = true)
	public User getOrCreateUser(String nickname) {
		return userRepository.findUserByNickname(nickname)
				.orElseGet(() -> User.builder()
						.nickname(nickname)
						.build()
		);
	}
}
