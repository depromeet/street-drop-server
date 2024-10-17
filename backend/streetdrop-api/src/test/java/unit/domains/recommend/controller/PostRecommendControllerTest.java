package unit.domains.recommend.controller;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.recommend.controller.PostRecommendController;
import com.depromeet.domains.recommend.dto.response.PostRecommendSentenceResponseDto;
import com.depromeet.domains.recommend.service.PostRecommendService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = PostRecommendController.class)
@WebMvcTest(controllers = {PostRecommendController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import(PostRecommendController.class)
@DisplayName("[API][Controller] PostRecommendController 테스트")
public class PostRecommendControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PostRecommendService postRecommendService;

    @DisplayName("[GET] 홈 화면 드랍 유도 - 무작위 문장 추천")
    @Nested
    class GetRandomSentenceTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("무작위 추천 문장 1개 조회")
            @Test
            void getOneRandomSentenceSuccess() throws Exception {
                var randomSentence = new PostRecommendSentenceResponseDto("random sentence");
                when(postRecommendService.getOneRandomSentence()).thenReturn(randomSentence);

                var response = mvc.perform(get("/post-recommend/random-sentence"));
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.sentence").value("random sentence"));
            }
        }
    }
}
