package com.depromeet.streetdrop.api.area.village.controller;

import com.depromeet.streetdrop.domains.area.village.service.VillageItemService;
import com.depromeet.streetdrop.apis.area.village.controller.VillageItemController;
import com.depromeet.streetdrop.domains.area.village.dto.response.VillageItemsCountResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {VillageItemController.class})
@Import(VillageItemController.class)
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
            @DisplayName("지역별 드랍 아이템 개수 조회")
            @Test
            void VillageItemCountTest() throws Exception {

                var VillageName = "종로구 사직동";
                var villageItemCountResponseDto = new VillageItemsCountResponseDto(1);
                given(villageItemService.countItemsByVillage(VillageName)).willReturn(villageItemCountResponseDto);
                var response = mvc.perform(
                        get("/villages/items/count")
                                .param("name", VillageName)
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.numberOfDroppedMusic").value(1));

            }
        }
    }
}
