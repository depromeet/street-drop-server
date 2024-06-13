package unit.domains.announcement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.depromeet.announcement.Announcement;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.announcement.dto.response.AnnouncementResponseDto;
import com.depromeet.domains.announcement.repository.AnnouncementRepository;
import com.depromeet.domains.announcement.service.AnnouncementService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] AnnouncementService 테스트")
class AnnouncementServiceTest {

    @InjectMocks
    AnnouncementService announcementService;

    @Mock
    AnnouncementRepository announcementRepository;

    @DisplayName("공지사항 전체 조회")
    @Nested
    class GetAnnouncementsTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("공지사항 0개 조회")
            @Test
            void getAnnouncementsTestSuccess1() {
                when(announcementRepository.findAll()).thenReturn(List.of());

                var result = announcementService.getAnnouncements();

                assertThat(result.getData()).isEmpty();
            }

            @DisplayName("공지사항 2개 조회")
            @Test
            void getAnnouncementsTestSuccess2() {
                List<Announcement> announcements = List.of(
                        new Announcement("Title 1", "Content 1"),
                        new Announcement("Title 2", "Content 2")
                );
                when(announcementRepository.findAll()).thenReturn(announcements);

                var result = announcementService.getAnnouncements();

                assertThat(result.getData()).isEqualTo(
                        List.of(
                                new AnnouncementResponseDto(announcements.get(0)),
                                new AnnouncementResponseDto(announcements.get(1))
                        )
                );
            }
        }
    }

    @DisplayName("공지사항 단건 조회")
    @Nested
    class GetAnnouncementTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("id가 일치하는 경우")
            @Test
            void getAnnouncementTestSuccess1() {
                var announcement = new Announcement("Title 1", "Content 1");
                when(announcementRepository.findById(1L)).thenReturn(Optional.of(announcement));

                var result = announcementService.getAnnouncement(1L);

                assertThat(result).isEqualTo(
                        new AnnouncementResponseDto(announcement)
                );
            }
        }

        @Nested
        @DisplayName("실패")
        class Fail {
            @DisplayName("id가 일치하지 않는 경우")
            @Test
            void getAnnouncementTestFail1() {
                var announcementId = 1L;

                when(announcementRepository.findById(announcementId)).thenReturn(Optional.empty());
                when(announcementRepository.findById(1L)).thenReturn(Optional.empty());

                assertThatThrownBy(() -> announcementService.getAnnouncement(announcementId))
                        .isInstanceOf(NotFoundException.class);
            }
        }
    }

}
