package unit.domains.recommend.controller;

import com.depromeet.common.error.GlobalExceptionHandler;
import com.depromeet.domains.recommend.controller.PostRecommendController;
import com.depromeet.domains.recommend.dto.response.PostRecommendSentenceResponseDto;
import com.depromeet.domains.recommend.service.PostRecommendService;
import com.depromeet.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import unit.annotation.MockAnonymousUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {PostRecommendController.class, ValidationAutoConfiguration.class})
@WebMvcTest(controllers = {PostRecommendController.class})
@Import({PostRecommendController.class, GlobalExceptionHandler.class})
@DisplayName("[API][Controller] PostRecommendController 테스트")
public class PostRecommendControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PostRecommendService postRecommendService;

    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .idfv("new-idfv")
                .build();
    }

    @DisplayName("[GET] 홈 화면 드랍 유도 - 무작위 문장 추천")
    @Nested
    @MockAnonymousUser
    class GetRandomSentenceTest {
        @Nested
        @DisplayName("성공")
        @MockAnonymousUser
        class Success {
            @DisplayName("무작위 추천 문장 1개 조회")
            @Test
            void getOneRandomSentenceSuccess1() throws Exception {

                var randomSentence = new PostRecommendSentenceResponseDto("random sentence");
                when(postRecommendService.getOneRandomSentence(any(User.class))).thenReturn(randomSentence);

                var response = mvc.perform(
                        get("/post-recommend/random-sentence")
                                .header("x-sdp-idfv", "new-idfv")
                );
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.sentence").value("random sentence"));
            }

            @DisplayName("무작위 추천 문장이 없는 경우")
            @Test
            void getOneRandomSentenceSuccess2() throws Exception {

                var emptySentence = PostRecommendSentenceResponseDto.empty();
                when(postRecommendService.getOneRandomSentence(any(User.class))).thenReturn(emptySentence);

                var response = mvc.perform(
                        get("/post-recommend/random-sentence")
                                .header("x-sdp-idfv", "new-idfv")
                );
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.sentence").isEmpty());
            }
        }
    }
}
