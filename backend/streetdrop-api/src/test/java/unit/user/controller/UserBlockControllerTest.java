package unit.user.controller;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.user.controller.UserBlockController;
import com.depromeet.domains.user.dto.response.BlockUserResponseDto;
import com.depromeet.domains.user.service.UserBlockService;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = UserBlockController.class)
@WebMvcTest(controllers = {UserBlockController.class})
@Import(UserBlockController.class)
@DisplayName("[API][Controller] UserBlockController 테스트")
public class UserBlockControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserBlockService userBlockService;

    @DisplayName("[POST] 유저 차단")
    @Nested
    class UserBlockTest {

        private long blockUserId;

        private User blockedUser;

        private BlockUserResponseDto blockUserResponseDto;


        /*
         * This Method is used to set User's id field.
         */
        private void setUserId(User user, long id) throws NoSuchFieldException, IllegalAccessException {
            Field idField = User.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(user, id);
        }

        @BeforeEach
        void setUp() throws NoSuchFieldException, IllegalAccessException {
            blockUserId = 456;

            blockedUser = User.builder()
                    .nickname("BlockedUser")
                    .idfv("idfv2")
                    .userLevelId(1L)
                    .musicApp(MusicApp.YOUTUBE_MUSIC)
                    .build();

            setUserId(blockedUser, 456);

            blockUserResponseDto = new BlockUserResponseDto(blockedUser);
        }

        @Nested
        @DisplayName("성공")
        @MockAnonymousUser
        class Success {
            @DisplayName("유저 차단")
            @Test
            void PostUserBlockTest() throws Exception {
                // Given
                given(userBlockService.blockUser(any(User.class), anyLong())).willReturn(blockUserResponseDto);

                // When
                var response = mvc.perform(
                        post("/users/block")
                                .header("x-sdp-idfv", "idfv1")
                                .param("blockUserID", String.valueOf(blockUserId))
                                .with(csrf())
                );


                // Then
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.userId").value(blockedUser.getId()))
                        .andExpect(jsonPath("$.nickname").value(blockedUser.getNickname()));
            }

        }
    }


    @DisplayName("[DELETE] 유저 차단 해제")
    @Nested
    class UserUnBlockTest {
        @Nested
        @DisplayName("성공")
        @MockAnonymousUser
        class Success {
            @DisplayName("유저 차단 해제 성공")
            @Test
            void DeleteUserUnBlockTest() throws Exception {
                // Given
                doNothing().when(userBlockService).unBlockUser(any(User.class), anyLong());

                // When
                var response = mvc.perform(
                        delete("/users/unblock")
                                .header("x-sdp-idfv", "idfv1")
                                .param("unblockUserId", String.valueOf(456))
                                .with(csrf())
                );

                // Then
                response.andExpect(status().isNoContent());
                verify(userBlockService).unBlockUser(any(User.class), anyLong());
            }

        }

    }
}
