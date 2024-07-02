package unit.domains.notice.service;

import com.depromeet.notice.Notice;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.notice.dto.response.NoticeListResponseDto;
import com.depromeet.domains.notice.dto.response.NoticeResponseDto;
import com.depromeet.domains.notice.dto.response.NewNoticeResponseDto;
import com.depromeet.domains.notice.repository.NoticeRepository;
import com.depromeet.domains.notice.service.NoticeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] NoticeService 테스트")
class NoticeServiceTest {

    @InjectMocks
    NoticeService noticeService;

    @Mock
    NoticeRepository noticeRepository;

    @DisplayName("공지사항 전체 조회")
    @Nested
    class GetNoticesTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("공지사항 0개 조회")
            @Test
            void getNoticesTestSuccess1() {
                when(noticeRepository.findAll()).thenReturn(List.of());

                var result = noticeService.getNotices();

                assertThat(result.getData()).isEmpty();
            }

            @DisplayName("공지사항 2개 조회")
            @Test
            void getNoticesTestSuccess2() {
                List<Notice> notices = List.of(
                        new Notice("Title 1", "Content 1"),
                        new Notice("Title 2", "Content 2")
                );
                when(noticeRepository.findAll()).thenReturn(notices);

                var result = noticeService.getNotices();

                assertThat(result.getData()).isEqualTo(
                        List.of(
                                new NoticeListResponseDto(notices.get(0)),
                                new NoticeListResponseDto(notices.get(1))
                        )
                );
            }
        }
    }

    @DisplayName("공지사항 단건 조회")
    @Nested
    class GetNoticeTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("id가 일치하는 경우")
            @Test
            void getNoticeTestSuccess1() {
                var notice = new Notice("Title 1", "Content 1");
                when(noticeRepository.findById(1L)).thenReturn(Optional.of(notice));

                var result = noticeService.getNotice(1L);

                assertThat(result).isEqualTo(
                        new NoticeResponseDto(notice)
                );
            }
        }

        @Nested
        @DisplayName("실패")
        class Fail {
            @DisplayName("id가 일치하지 않는 경우")
            @Test
            void getNoticeTestFail1() {
                var noticeId = 1L;

                when(noticeRepository.findById(noticeId)).thenReturn(Optional.empty());
                when(noticeRepository.findById(1L)).thenReturn(Optional.empty());

                assertThatThrownBy(() -> noticeService.getNotice(noticeId))
                        .isInstanceOf(NotFoundException.class);
            }
        }
    }


    @DisplayName("신규 공지사항 여부 조회")
    @Nested
    class HasNewNoticeTest {
        @DisplayName("조회할 공지사항 있는 경우")
        @Test
        void hasNewNoticeTestSuccess1() {
            var expectedResponse = new NewNoticeResponseDto(true);

            when(noticeRepository.existsByIdGreaterThan(-1L)).thenReturn(true);

            var result = noticeService.hasNewNotice(-1L);

            assertThat(result).isEqualTo(expectedResponse);
        }

        @DisplayName("조회할 공지사항 없는 경우")
        @Test
        void hasNewNoticeTestSuccess2() {
            var expectedResponse = new NewNoticeResponseDto(false);

            when(noticeRepository.existsByIdGreaterThan(-1L)).thenReturn(false);

            var result = noticeService.hasNewNotice(-1L);

            assertThat(result).isEqualTo(expectedResponse);
        }
    }
}
