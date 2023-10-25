package unit.domains.user.controller;

import com.depromeet.domains.user.controller.UserController;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.domains.user.service.UserLevelService;
import com.depromeet.domains.user.service.UserService;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

    @MockBean
    UserLevelService userLevelService;

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
                User user = User.builder()
                        .nickname("Guest")
                        .idfv("new-idfv")
                        .userLevelId(1L)
                        .musicApp(MusicApp.YOUTUBE_MUSIC).build();

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

    @DisplayName("[Patch] 유저 닉네임 변경")
    @Nested
    class UserNicknameChangeTest {


        @Nested
        @DisplayName("성공")
        @MockAnonymousUser
        class Success {
            private UserResponseDto userResponseDto;

            @BeforeEach
            void setUp(){

                User user = User.builder()
                        .nickname("Guest")
                        .idfv("new-idfv")
                        .userLevelId(1L)
                        .musicApp(MusicApp.YOUTUBE_MUSIC).build();

                userResponseDto = new UserResponseDto(user);

            }

            @DisplayName("유저 닉네임 변경 성공")
            @Test
            void GetUserInfoTest() throws Exception {
                given(userService.getUserInfo(any(User.class))).willReturn(userResponseDto);

                var response = mvc.perform(
                        patch("/users/me/nickname")
                                .header("x-sdp-idfv", "new-idfv")
                                .param("nickname", " newNick")
                                .with(csrf())
                );

                response.andExpect(status().isOk());

            }

        }



        @Nested
        @DisplayName("실패")
        @MockAnonymousUser
        class Fail {

            private UserResponseDto userResponseDto;

            @BeforeEach
            void setUp(){

                User user = User.builder()
                        .nickname("Guest")
                        .idfv("new-idfv")
                        .userLevelId(1L)
                        .musicApp(MusicApp.YOUTUBE_MUSIC).build();

                userResponseDto = new UserResponseDto(user);

            }


            @DisplayName("유저 닉네임 변경 실패 - 변경 닉네임이 요청이 없을 때")
            @Test
            void GetUserInfoTest_ChangeNicknameIsNotExist() throws Exception {
                given(userService.getUserInfo(any(User.class))).willReturn(userResponseDto);

                var response = mvc.perform(
                        patch("/users/me/nickname")
                                .header("x-sdp-idfv", "new-idfv")
                                .with(csrf())
                );

                response.andExpect(status().isBadRequest());

            }


            @DisplayName("유저 닉네임 변경 실패 - 닉네임 사이즈가 너무 짧을때")
            @Test
            void GetUserInfoTest_SizeViolation_Short() throws Exception {
                given(userService.getUserInfo(any(User.class))).willReturn(userResponseDto);

                var response = mvc.perform(
                        patch("/users/me/nickname")
                                .header("x-sdp-idfv", "new-idfv")
                                .param("nickname", "")
                                .with(csrf())
                );

                response.andExpect(status().isBadRequest());

            }



            @DisplayName("유저 닉네임 변경 실패 - 닉네임 사이즈가 너무 길때")
            @Test
            void GetUserInfoTest_SizeViolation_Long() throws Exception {
                given(userService.getUserInfo(any(User.class))).willReturn(userResponseDto);

                var response = mvc.perform(
                        patch("/users/me/nickname")
                                .header("x-sdp-idfv", "new-idfv")
                                .param("nickname", " thisnicknameistoolong")
                                .with(csrf())
                );

                response.andExpect(status().isBadRequest());

            }
        }

    }

}
