package unit.domains.village.controller;

import com.depromeet.domains.village.controller.VillageItemController;
import com.depromeet.domains.village.dto.request.VillageItemsRequestDto;
import com.depromeet.domains.village.dto.response.VillageItemsCountResponseDto;
import com.depromeet.domains.village.service.VillageItemService;
import com.depromeet.common.error.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = VillageItemController.class)
@WebMvcTest(controllers = {VillageItemController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import({VillageItemController.class, GlobalExceptionHandler.class})
@DisplayName("[API][Controller] VillageItemController 테스트")
public class VillageItemControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    VillageItemService villageItemService;

    @DisplayName("[GET] 지역별 드랍 아이템 개수 조회")
    @Nested
    class VillageItemCountTest {
        double latitude;
        double longitude;

        @BeforeEach
        void setUp() {
            latitude = 37.123456;
            longitude = 127.123456;
        }
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("지역별 드랍 아이템 개수 조회")
            @Test
            void villageItemCountTest() throws Exception {

                var villageItemsCountResponseDto = new VillageItemsCountResponseDto(1, "종로구 사직동");
                given(villageItemService.countItemsInVillageByLocation(any(VillageItemsRequestDto.class))).willReturn(villageItemsCountResponseDto);

                var response = mvc.perform(
                        get("/villages/items/count")
                                .param("latitude", String.valueOf(latitude))
                                .param("longitude", String.valueOf(longitude))
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.numberOfDroppedMusic").value(1))
                        .andExpect(jsonPath("$.villageName").value("종로구 사직동"));
            }
        }
        @Nested
        @DisplayName("실패")
        class Fail {
            @DisplayName("Latitude 유효성 검사 실패 - 값이 없는 경우")
            @Test
            void latitudeNotExist() throws Exception {

                var response = mvc.perform(
                        get("/villages/items/count")
                                .param("longitude", String.valueOf(longitude))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Latitude is required"));

            }

            @DisplayName("Latitude 유효성 검사 실패 - 범위에 맞지 않는 경우")
            @Test
            void latitudeOutOfRange() throws Exception {

                var response = mvc.perform(
                        get("/villages/items/count")
                                .param("latitude", String.valueOf(1000.0))
                                .param("longitude", String.valueOf(longitude))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Not valid latitude, must be between -90 and 90"));

            }

            @DisplayName("Longitude 유효성 검사 실패 - 값이 없는 경우")
            @Test
            void longitudeNotExist() throws Exception {

                var response = mvc.perform(
                        get("/villages/items/count")
                                .param("latitude", String.valueOf(latitude))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Longitude is required"));

            }

            @DisplayName("Longitude 유효성 검사 실패 - 범위에 맞지 않는 경우")
            @Test
            void longitudeOutOfRange() throws Exception {

                var response = mvc.perform(
                        get("/villages/items/count")
                                .param("latitude", String.valueOf(latitude))
                                .param("longitude", String.valueOf(1000.0))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Not valid longitude, must be between -180 and 180"));

            }
        }
    }
}
