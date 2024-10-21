package unit.domains.recommend.service;

import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.recommend.repository.PostRecommendSentenceRepository;
import com.depromeet.domains.recommend.repository.UserRecommendSendHistoryRepository;
import com.depromeet.domains.recommend.service.PostRecommendService;
import com.depromeet.recommend.post.PostRecommendSentence;
import com.depromeet.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] PostRecommendService 테스트")
public class PostRecommendServiceTest {

    @InjectMocks
    private PostRecommendService postRecommendService;

    @Mock
    private PostRecommendSentenceRepository postRecommendSentenceRepository;

    @Mock
    private UserRecommendSendHistoryRepository userRecommendSendHistoryRepository;

    User user;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        user = User.builder().build();
        Field userIdField = User.class.getDeclaredField("id");
        userIdField.setAccessible(true);
        userIdField.set(user, 1L);
    }

    @DisplayName("무작위 문장 추천")
    @Nested
    class GetOneRandomSentenceTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("이미 추천 문장을 받은 사용자인 경우")
            @Test
            void getOneRandomSentenceSuccess1() {
                given(userRecommendSendHistoryRepository.isSent(user.getId())).willReturn(true);
                var result = postRecommendService.getOneRandomSentence(user);

                assertThat(result.sentence()).isNull();
            }

            @DisplayName("무작위 추천 문장 1개 조회")
            @Test
            void getOneRandomSentenceSuccess2() {
                List<PostRecommendSentence> sentences = List.of(
                        new PostRecommendSentence("First sentence"),
                        new PostRecommendSentence("Second sentence"),
                        new PostRecommendSentence("Third sentence")
                );

                given(postRecommendSentenceRepository.findAll()).willReturn(sentences);
                given(userRecommendSendHistoryRepository.isSent(user.getId())).willReturn(false);
                var result = postRecommendService.getOneRandomSentence(user);

                assertThat(result).isNotNull();
                assertThat(result.sentence()).isIn(
                        "First sentence",
                        "Second sentence",
                        "Third sentence"
                );
            }
        }

        @Nested
        @DisplayName("실패")
        class Fail {
            @DisplayName("저장소에 추천 문장이 없는 경우")
            @Test
            void getOneRandomSentenceFail() {
                given(userRecommendSendHistoryRepository.isSent(user.getId())).willReturn(false);
                given(postRecommendSentenceRepository.findAll()).willReturn(List.of());

                assertThatThrownBy(() -> postRecommendService.getOneRandomSentence(user))
                        .isInstanceOf(NotFoundException.class);
            }
        }
    }

}
