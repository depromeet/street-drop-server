package unit.user.controller;

import com.depromeet.domains.user.controller.UserController;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.user.User;
import com.depromeet.domains.user.service.UserService;
import com.depromeet.user.vo.MusicApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import unit.annotation.MockAnonymousUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = UserController.class)
@WebMvcTest(controllers = {UserController.class})
@Import(UserController.class)
@DisplayName("[API][Controller] UserController 테스트")
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @DisplayName("[GET] 유저 정보 조회")
    @Nested
    class GetUserInfoTest {
        @Nested
        @DisplayName("성공")
        @MockAnonymousUser
        class Success {
            @DisplayName("유저 정보 조회")
            @Test
            void GetUserInfoTest() throws Exception {
                User user = User.builder().nickname("Guest").idfv("new-idfv").musicApp(MusicApp.YOUTUBE_MUSIC).build();
                UserResponseDto userResponseDto = new UserResponseDto(user);
                given(userService.getUserInfo(any(User.class))).willReturn(userResponseDto);

                var response = mvc.perform(
                        get("/users/me")
                                .header("x-sdp-idfv", "new-idfv")
                );

                response.andExpect(status().isOk());

            }
        }
    }


}
