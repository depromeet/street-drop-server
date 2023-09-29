package unit.domains.item.controller;


import com.depromeet.common.error.GlobalExceptionHandler;
import com.depromeet.domains.item.controller.ItemClaimController;
import com.depromeet.domains.item.dto.request.ItemClaimRequestDto;
import com.depromeet.domains.item.service.ItemClaimService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = ItemClaimController.class)
@WebMvcTest(controllers = {ItemClaimController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import({ItemClaimController.class, GlobalExceptionHandler.class})
@DisplayName("[API][Controller] ItemController 테스트")
public class ItemClaimControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ItemClaimService itemClaimService;


    @DisplayName("[POST] 아이템 신고하기")
    @Nested
    class ItemClaimTest {

        @DisplayName("아이템 신고하기 - 성공")
        @Nested
        class Success {
            @DisplayName("아이템 신고하기 - 성공")
            @Test
            void itemClaimSuccess() throws Exception {
                var itemClaimRequestDto = new ItemClaimRequestDto(1L, "신고 사유");
                var contentBody = objectMapper.writeValueAsString(itemClaimRequestDto);

                var response = mvc.perform(
                        post("/items/claim")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentBody)
                );

                response.andExpect(status().isNoContent());
            }

        }

        @DisplayName("아이템 신고하기 - 실패")
        @Nested
        class Fail {

            @DisplayName("아이템 신고하기 - 신고사유가 없는 경우")
            @Test
            void itemClaimFail_NoClaimReason() throws Exception {
                var itemClaimRequestDto = new ItemClaimRequestDto(1L, null);
                var contentBody = objectMapper.writeValueAsString(itemClaimRequestDto);

                var response = mvc.perform(
                        post("/items/claim")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentBody)
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Claim reason is required"))
                ;
            }

            @DisplayName("아이템 신고하기 - 신고사유가 없는 경우")
            @Test
            void itemClaimFail_NoItemId() throws Exception {
                var itemClaimRequestDto = new ItemClaimRequestDto(null, "Bad Item Contents");
                var contentBody = objectMapper.writeValueAsString(itemClaimRequestDto);

                var response = mvc.perform(
                        post("/items/claim")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contentBody)
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("must not be null"))
                ;
            }

        }
    }


}
