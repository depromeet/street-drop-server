package com.depromeet.domains.user.service;

import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.domains.user.repository.DefaultNickNameRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
    private final DefaultNickNameRepository defaultNickNameRepository;

	@Transactional(readOnly = true)
	public User getOrCreateUser(String nickname) {
		return userRepository.findUserByNickname(nickname)
				.orElseGet(() -> User.builder()
						.nickname(nickname)
						.build()
		);
	}

    @Transactional
    public User getOrCreateUserByIdfv(String idfv) {
        Optional<User> user = userRepository.findUserByIdfv(idfv);
        if (user.isPresent()) {
            return user.get();
        } else {
            User newUser = User.builder()
                    .nickname(generateDefaultNickname())
                    .idfv(idfv)
                    .build();
            return userRepository.save(newUser);
        }
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(User user) {
        return new UserResponseDto(user);
    }

    private String generateDefaultNickname() {
        return defaultNickNameRepository.getDefaultNickNameByRandom();
    }
}
