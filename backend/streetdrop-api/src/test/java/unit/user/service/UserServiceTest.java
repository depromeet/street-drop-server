package unit.user.service;

import com.depromeet.domains.user.dto.response.UserDetailResponseDto;
import com.depromeet.user.User;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.domains.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("유저 정보 조회")
    @Nested
    class GetUserInfoTest {
        @DisplayName("유저 정보 조회 - 성공")
        @Test
        void getUserInfoTestSuccess() {
            User user = new User();
            UserDetailResponseDto userDetailResponseDto = new UserDetailResponseDto(user);
            var result = userService.getUserInfo(user);

            assertThat(result).isEqualTo(userDetailResponseDto);
        }

    }

    @DisplayName("IDFV로 유저 조회 또는 생성")
    @Nested
    class GetOrCreateUserByIdfv {

        @DisplayName("IDFV로 유저 조회 성공")
        @Test
        void getOrCreateUserByIdfvTestSuccess() {
            User user = new User();
            when(userRepository.findUserByIdfv(anyString())).thenReturn(Optional.of(user));
            var result = userService.getOrCreateUserByIdfv("idfv");

            verify(userRepository).findUserByIdfv("idfv");
            assertThat(result).isEqualTo(user);
        }

        @DisplayName("IDFV로 유저 조회 실패 - 유저 생성")
        @Test
        void getOrCreateUserByIdfvTestSuccess2() {
            User user = new User();
            when(userRepository.findUserByIdfv(anyString())).thenReturn(Optional.empty());
            when(userRepository.save(any(User.class))).thenReturn(user);
            var result = userService.getOrCreateUserByIdfv("idfv");

            verify(userRepository).findUserByIdfv("idfv");
            verify(userRepository, times(1)).save(any(User.class));
            assertThat(result).isEqualTo(user);
        }

    }


}
