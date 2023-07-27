package unit.user.service;

import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.domains.user.repository.DefaultNickNameRepository;
import com.depromeet.user.User;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.domains.user.service.UserService;
import com.depromeet.user.vo.MusicApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] UserService 테스트")
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    DefaultNickNameRepository defaultNickNameRepository;

    @DisplayName("유저 정보 조회")
    @Nested
    class GetUserInfoTest {
        @DisplayName("유저 정보 조회 - 성공")
        @Test
        void getUserInfoTestSuccess() {
            User user = User.builder()
                    .nickname("testUser")
                    .idfv("test-idfv")
                    .musicApp(MusicApp.YOUTUBE_MUSIC)
                    .userLevelId(1L)
                    .build();
            UserResponseDto userResponseDto = new UserResponseDto(user);
            var result = userService.getUserInfo(user);

            assertThat(result).isEqualTo(userResponseDto);
        }

    }

    @DisplayName("IDFV로 유저 조회 또는 생성")
    @Nested
    class GetOrCreateUserByIdfv {

        @DisplayName("IDFV로 유저 조회 성공")
        @Test
        void getOrCreateUserByIdfvTestSuccess() {
            User user =  User.builder().build();
            when(userRepository.findUserByIdfv(anyString())).thenReturn(Optional.of(user));
            var result = userService.getOrCreateUserByIdfv("idfv");

            verify(userRepository).findUserByIdfv("idfv");
            assertThat(result).isEqualTo(user);
        }

        @DisplayName("IDFV로 유저 조회 실패 - 유저 생성")
        @Test
        void getOrCreateUserByIdfvTestSuccess2() {
            User user = User.builder().build();
            when(userRepository.findUserByIdfv(anyString())).thenReturn(Optional.empty());
            when(defaultNickNameRepository.getDefaultNickNameByRandom()).thenReturn("nickname");
            when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);
            var result = userService.getOrCreateUserByIdfv("idfv");

            verify(userRepository).findUserByIdfv("idfv");
            verify(userRepository, times(1)).saveAndFlush(any(User.class));
            assertThat(result).isEqualTo(user);
        }

    }

    @DisplayName("유저 정보 수정")
    @Nested
    class ChangeUserInfoTest {

        @DisplayName("뮤직 앱을 수정한다 - 성공")
        @ParameterizedTest
        @EnumSource(MusicApp.class)
        void changeMusicAppSuccess(MusicApp musicApp) {
            User user =  User.builder().build();
            user.changeMusicApp(musicApp);
            assertThat(user.getMusicApp()).isEqualTo(musicApp);
        }
    }
}
