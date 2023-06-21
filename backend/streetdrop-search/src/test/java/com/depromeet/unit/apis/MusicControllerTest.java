package com.depromeet.unit.apis;

import com.depromeet.apis.MusicController;
import com.depromeet.music.service.MusicService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {MusicController.class})
@Import(MusicController.class)
@DisplayName("[API][Controller] MusicController 테스트")
public class MusicControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MusicService musicService;

    @DisplayName("[GET] 음악 검색")
    @Nested
    class SearchMusicTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("음악 검색")
            @Test
            void SearchMusicTest() throws Exception {
                String keyword = "new jeans";
                given(musicService.searchMusic(keyword)).willReturn(null);

                var response = mvc.perform(
                        get("/music")
                                .param("keyword", keyword)
                );
                response.andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("실패")
        class Fail {
            @DisplayName("키워드가 없는 경우")
            @Test
            void SearchMusicTest_WhenKeywordNotExist() throws Exception {
                var response = mvc.perform(
                        get("/music")
                );
                response.andExpect(status().isBadRequest());
            }

            @DisplayName("키워드가 빈 문자열인 경우")
            @Test
            void SearchMusicTest_WhenKeywordEmptyString() throws Exception {
                var response = mvc.perform(
                        get("/music")
                                .param("keyword", "")
                );
                response.andExpect(status().isBadRequest());
            }
        }
    }





}
