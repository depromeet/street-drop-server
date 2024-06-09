package unit.domains.announcement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.depromeet.announcement.Announcement;
import com.depromeet.common.error.GlobalExceptionHandler;
import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.announcement.controller.AnnouncementController;
import com.depromeet.domains.announcement.dto.response.AnnouncementResponseDto;
import com.depromeet.domains.announcement.dto.response.AnnouncementsResponseDto;
import com.depromeet.domains.announcement.service.AnnouncementService;
import java.util.List;
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

@ContextConfiguration(classes = AnnouncementController.class)
@WebMvcTest(controllers = {AnnouncementController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import({AnnouncementController.class, GlobalExceptionHandler.class})
@DisplayName("[API][Controller] AnnouncementController 테스트")
public class AnnouncementControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AnnouncementService announcementService;

    @DisplayName("[GET] 공지사항 전체 조회")
    @Nested
    class GetAnnouncementsTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("공지사항 0개 조회")
            @Test
            void getAnnouncementsTestSuccess1() throws Exception {
                var announcementsResponseDto = new AnnouncementsResponseDto(List.of());
                when(announcementService.getAnnouncements()).thenReturn(announcementsResponseDto);

                var response = mvc.perform(
                        get("/announcements")
                );
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.data").isArray())
                        .andExpect(jsonPath("$.data.length()").value(0));
            }

            @DisplayName("공지사항 2개 조회")
            @Test
            void getAnnouncementsTestSuccess2() throws Exception {
                var announcementResponseDto1 = new AnnouncementResponseDto(new Announcement("Title 1", "Content 1"));
                var announcementResponseDto2 = new AnnouncementResponseDto(new Announcement("Title 2", "Content 2"));
                var announcementsResponseDto = new AnnouncementsResponseDto(List.of(announcementResponseDto1, announcementResponseDto2));
                when(announcementService.getAnnouncements()).thenReturn(announcementsResponseDto);

                var response = mvc.perform(get("/announcements"));
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.data").isArray())
                        .andExpect(jsonPath("$.data.length()").value(2))
                        .andExpect(jsonPath("$.data[0].title").value("Title 1"))
                        .andExpect(jsonPath("$.data[0].content").value("Content 1"))
                        .andExpect(jsonPath("$.data[1].title").value("Title 2"))
                        .andExpect(jsonPath("$.data[1].content").value("Content 2"));
            }
        }
    }

    @DisplayName("[GET] 공지사항 단건 조회")
    @Nested
    class GetAnnouncementTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("id가 일치하는 경우")
            @Test
            void getAnnouncementTestSuccess1() throws Exception {
                var announcementId = 1L;
                var announcementResponseDto = new AnnouncementResponseDto(new Announcement("Title 1", "Content 1"));
                when(announcementService.getAnnouncement(announcementId)).thenReturn(announcementResponseDto);

                var response = mvc.perform(get("/announcements/{announcementId}", announcementId));
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.title").value("Title 1"))
                        .andExpect(jsonPath("$.content").value("Content 1"));
            }
        }

        @Nested
        @DisplayName("실패")
        class Fail {
            @DisplayName("id가 일치하지 않는 경우")
            @Test
            void getAnnouncementTestFail1() throws Exception {
                var announcementId = 1L;
                when(announcementService.getAnnouncement(announcementId))
                        .thenThrow(new NotFoundException(CommonErrorCode.NOT_FOUND, announcementId));

                var response = mvc.perform(get("/announcements/{announcementId}", announcementId));
                response.andExpect(status().isNotFound());
            }
        }
    }

}
