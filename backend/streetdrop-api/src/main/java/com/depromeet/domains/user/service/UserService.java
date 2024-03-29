package com.depromeet.domains.user.service;

import com.depromeet.domains.user.dto.response.UserDistanceResponseDto;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.domains.user.repository.DefaultNickNameRepository;
import com.depromeet.domains.user.repository.UserLevelRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    public static final long USER_LEVEL_ID = 1L;
    private final UserRepository userRepository;
    private final DefaultNickNameRepository defaultNickNameRepository;
    private final UserLevelRepository userLevelRepository;
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
		            .musicApp(MusicApp.YOUTUBE_MUSIC)
                    .userLevelId(USER_LEVEL_ID)
                    .build();
            return userRepository.saveAndFlush(newUser);
        }
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(User user) {
        return new UserResponseDto(user);
    }


    private String generateDefaultNickname() {
        return defaultNickNameRepository.getDefaultNickNameByRandom();
    }

    @Transactional
    public UserResponseDto changeNickname(User user, String nickname) {
        var changedUser = user.changeNickname(nickname);

        userRepository.save(changedUser);

        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto changeMusicApp(User user, MusicApp musicApp) {
        user.changeMusicApp(musicApp);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserDistanceResponseDto getUserDistance(User user) {
        var defaultDistance = 600;
        var userLevelId = user.getUserLevelId();
        return userLevelRepository.findById(userLevelId)
                .map(userLevel -> new UserDistanceResponseDto(userLevel.getDistance()))
                .orElseGet(() -> new UserDistanceResponseDto(defaultDistance));
    }
}
