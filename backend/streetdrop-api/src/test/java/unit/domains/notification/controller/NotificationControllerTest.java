package unit.domains.notification.controller;


import com.depromeet.common.error.GlobalExceptionHandler;
import com.depromeet.domains.notification.controller.NotificationController;
import com.depromeet.domains.notification.dto.NotificationTokenDto;
import com.depromeet.domains.notification.service.NotificationService;
import com.depromeet.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import unit.annotation.MockAnonymousUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = NotificationController.class)
@WebMvcTest(controllers = {NotificationController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import({NotificationController.class, GlobalExceptionHandler.class})
@DisplayName("[API][Controller] ItemController 테스트")
public class NotificationControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    NotificationService notificationService;

    @DisplayName("[POST] 푸시 토큰 등록")
    @Nested
    class SaveTokenTest {

        @Nested
        @DisplayName("성공")
        @MockAnonymousUser
        class Success {

            @DisplayName("푸시 토큰 등록")
            @Test
            void saveTokenTest_Success() throws Exception {
                NotificationTokenDto notificationTokenDto = new NotificationTokenDto("token");
                var body = objectMapper.writeValueAsString(notificationTokenDto);
                doNothing().when(notificationService).saveToken(any(User.class), any(NotificationTokenDto.class));

                var response = mvc.perform(
                        post("/notifications/tokens")
                                .header("x-sdp-idfv", "idfv1")
                                .contentType("application/json")
                                .content(body)
                );

                response.andExpect(status().isNoContent());
            }

        }

        @Nested
        @DisplayName("실패")
        @MockAnonymousUser
        class Fail {

            @DisplayName("푸시 토큰 등록 실패 - 요청 토큰이 없을때")
            @Test
            void saveTokenFailTest_NotificationToken_Null() throws Exception {
                NotificationTokenDto notificationTokenDto = new NotificationTokenDto(null);
                var body = objectMapper.writeValueAsString(notificationTokenDto);
                doNothing().when(notificationService).saveToken(any(User.class), any(NotificationTokenDto.class));

                var response = mvc.perform(
                        post("/notifications/tokens")
                                .header("x-sdp-idfv", "idfv1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)

                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Token is required"));
            }


        }

    }

}
