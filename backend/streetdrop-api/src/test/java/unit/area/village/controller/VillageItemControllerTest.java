package unit.area.village.controller;

import com.depromeet.area.village.controller.VillageItemController;
import com.depromeet.area.village.dto.request.VillageItemsRequestDto;
import com.depromeet.area.village.dto.response.VillageItemsCountResponseDto;
import com.depromeet.area.village.service.VillageItemService;
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
        @Nested
        @DisplayName("성공")
        class Success {
            double latitude;
            double longitude;

            @BeforeEach
            void setUp() {
                latitude = 37.123456;
                longitude = 127.123456;
            }
            @DisplayName("지역별 드랍 아이템 개수 조회")
            @Test
            void VillageItemCountTest() throws Exception {

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
    }
}
