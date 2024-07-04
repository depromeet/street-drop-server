package unit.domains.notice.controller;

import com.depromeet.notice.Notice;
import com.depromeet.common.dto.MetaInterface;
import com.depromeet.common.dto.PageMetaResponseDto;
import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.common.error.GlobalExceptionHandler;
import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.notice.controller.NoticeController;
import com.depromeet.domains.notice.dto.response.NoticeListResponseDto;
import com.depromeet.domains.notice.dto.response.NoticeResponseDto;
import com.depromeet.domains.notice.dto.response.NewNoticeResponseDto;
import com.depromeet.domains.notice.service.NoticeService;
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

@ContextConfiguration(classes = NoticeController.class)
@WebMvcTest(controllers = {NoticeController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import({NoticeController.class, GlobalExceptionHandler.class})
@DisplayName("[API][Controller] NoticeController 테스트")
public class NoticeControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    NoticeService noticeService;

    @DisplayName("[GET] 공지사항 전체 조회")
    @Nested
    class GetNoticesTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("공지사항 0개 조회")
            @Test
            void getNoticesTestSuccess1() throws Exception {
                var meta = PageMetaResponseDto.builder()
                        .page(0)
                        .size(0)
                        .totalPage(0)
                        .firstPage(true)
                        .lastPage(true)
                        .build();
                PaginationResponseDto<NoticeListResponseDto, MetaInterface> paginationResponseDto = new PaginationResponseDto<>(List.of(), meta);
                when(noticeService.getNotices()).thenReturn(paginationResponseDto);

                var response = mvc.perform(get("/notices"));
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
            void getNoticesTestSuccess2() throws Exception {
                var noticeResponseDto = List.of(
                        new NoticeListResponseDto(new Notice("Title 1", "Content 1")),
                        new NoticeListResponseDto(new Notice("Title 2", "Content 2"))
                );
                var meta = PageMetaResponseDto.builder()
                        .page(0)
                        .size(0)
                        .totalPage(0)
                        .firstPage(true)
                        .lastPage(true)
                        .build();
                PaginationResponseDto<NoticeListResponseDto, MetaInterface> paginationResponseDto = new PaginationResponseDto<>(noticeResponseDto, meta);
                when(noticeService.getNotices()).thenReturn(paginationResponseDto);

                var response = mvc.perform(get("/notices"));
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
    class GetNoticeTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("id가 일치하는 경우")
            @Test
            void getNoticeTestSuccess1() throws Exception {
                var noticeId = 1L;
                var noticeResponseDto = new NoticeResponseDto(new Notice("Title 1", "Content 1"));
                when(noticeService.getNotice(noticeId)).thenReturn(noticeResponseDto);

                var response = mvc.perform(get("/notices/{noticeId}", noticeId));
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
            void getNoticeTestFail1() throws Exception {
                var noticeId = 1L;
                when(noticeService.getNotice(noticeId))
                        .thenThrow(new NotFoundException(CommonErrorCode.NOT_FOUND, noticeId));

                var response = mvc.perform(get("/notices/{noticeId}", noticeId));
                response.andExpect(status().isNotFound());
            }
        }
    }


    @DisplayName("[GET] 신규 공지사항 여부 조회")
    @Nested
    class HasNewNoticeTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("신규 공지사항이 없는 경우")
            @Test
            void hasNewNoticeTestSuccess1() throws Exception {
                var lastNoticeId = 1L;
                var newNoticeResponseDto = new NewNoticeResponseDto(false);
                when(noticeService.hasNewNotice(lastNoticeId)).thenReturn(newNoticeResponseDto);

                var response = mvc.perform(get("/notices/new?lastNoticeId={lastNoticeId}", lastNoticeId));
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.hasNewNotice").value(false));

            }

            @DisplayName("신규 공지사항이 있는 경우")
            @Test
            void hasNewNoticeTestSuccess2() throws Exception {
                var lastNoticeId = 1L;
                var newNoticeResponseDto = new NewNoticeResponseDto(true);
                when(noticeService.hasNewNotice(lastNoticeId)).thenReturn(newNoticeResponseDto);

                var response = mvc.perform(get("/notices/new?lastNoticeId={lastNoticeId}", lastNoticeId));
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.hasNewNotice").value(true));
            }
        }

    }
}
