package com.streetdrop.domains.user.service;

import com.streetdrop.domains.user.dto.response.UserResponseDto;
import com.streetdrop.domains.user.repository.DefaultNickNameRepository;
import com.streetdrop.domains.user.repository.UserRepository;
import com.streetdrop.user.User;
import com.streetdrop.user.vo.MusicApp;
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
}
