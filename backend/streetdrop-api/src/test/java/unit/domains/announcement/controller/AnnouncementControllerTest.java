package unit.domains.announcement.controller;

import com.depromeet.announcement.Announcement;
import com.depromeet.common.dto.MetaInterface;
import com.depromeet.common.dto.PageMetaResponseDto;
import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.common.error.GlobalExceptionHandler;
import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.announcement.controller.AnnouncementController;
import com.depromeet.domains.announcement.dto.response.AnnouncementListResponseDto;
import com.depromeet.domains.announcement.dto.response.AnnouncementResponseDto;
import com.depromeet.domains.announcement.dto.response.NewAnnouncementResponseDto;
import com.depromeet.domains.announcement.service.AnnouncementService;
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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                var meta = PageMetaResponseDto.builder()
                        .page(0)
                        .size(0)
                        .totalPage(0)
                        .firstPage(true)
                        .lastPage(true)
                        .build();
                PaginationResponseDto<AnnouncementListResponseDto, MetaInterface> paginationResponseDto = new PaginationResponseDto<>(List.of(), meta);
                when(announcementService.getAnnouncements()).thenReturn(paginationResponseDto);

                var response = mvc.perform(get("/announcements"));
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.data").isArray())
                        .andExpect(jsonPath("$.data.length()").value(0))
                        .andExpect(jsonPath("$.meta.page").value(0))
                        .andExpect(jsonPath("$.meta.size").value(0))
                        .andExpect(jsonPath("$.meta.totalPage").value(0))
                        .andExpect(jsonPath("$.meta.firstPage").value(true))
                        .andExpect(jsonPath("$.meta.lastPage").value(true));
            }

            @DisplayName("공지사항 2개 조회")
            @Test
            void getAnnouncementsTestSuccess2() throws Exception {
                var announcementResponseDto = List.of(
                        new AnnouncementListResponseDto(new Announcement("Title 1", "Content 1")),
                        new AnnouncementListResponseDto(new Announcement("Title 2", "Content 2"))
                );
                var meta = PageMetaResponseDto.builder()
                        .page(0)
                        .size(0)
                        .totalPage(0)
                        .firstPage(true)
                        .lastPage(true)
                        .build();
                PaginationResponseDto<AnnouncementListResponseDto, MetaInterface> paginationResponseDto = new PaginationResponseDto<>(announcementResponseDto, meta);
                when(announcementService.getAnnouncements()).thenReturn(paginationResponseDto);

                var response = mvc.perform(get("/announcements"));
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.data").isArray())
                        .andExpect(jsonPath("$.data.length()").value(2))
                        .andExpect(jsonPath("$.data[0].title").value("Title 1"))
                        .andExpect(jsonPath("$.data[1].title").value("Title 2"));
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


    @DisplayName("[GET] 신규 공지사항 여부 조회")
    @Nested
    class HasNewAnnouncementTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("신규 공지사항이 없는 경우")
            @Test
            void hasNewAnnouncementTestSuccess1() throws Exception {
                var lastAnnouncementId = 1L;
                var announcementResponseDto = new NewAnnouncementResponseDto(false);
                when(announcementService.hasNewAnnouncement(lastAnnouncementId)).thenReturn(announcementResponseDto);

                var response = mvc.perform(get("/announcements/new?lastAnnouncementId={lastAnnouncementId}", lastAnnouncementId));
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.hasNewAnnouncement").value(false));

            }

            @DisplayName("신규 공지사항이 있는 경우")
            @Test
            void hasNewAnnouncementTestSuccess2() throws Exception {
                var lastAnnouncementId = 1L;
                var announcementResponseDto = new NewAnnouncementResponseDto(true);
                when(announcementService.hasNewAnnouncement(lastAnnouncementId)).thenReturn(announcementResponseDto);

                var response = mvc.perform(get("/announcements/new?lastAnnouncementId={lastAnnouncementId}", lastAnnouncementId));
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.hasNewAnnouncement").value(true));
            }
        }

    }
}