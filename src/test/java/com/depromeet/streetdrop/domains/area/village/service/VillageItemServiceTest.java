package com.depromeet.streetdrop.domains.area.village.service;

import com.depromeet.streetdrop.apis.area.village.dto.response.VillageItemsCountResponseDto;
import com.depromeet.streetdrop.domains.area.village.repository.VillageAreaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] VillageItemService 테스트")
public class VillageItemServiceTest {

    @InjectMocks
    VillageItemService villageItemService;

    @Mock
    VillageAreaRepository villageAreaRepository;

    @DisplayName("지역별 드랍 아이템 개수 조회")
    @Nested
    class VillageItemCountTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("지역별 드랍 아이템 개수 조회")
            @Test
            void VillageItemCountTest() {
                var villageName = "서울특별시 강남구";
                when(villageAreaRepository.countItemsByVillageName(any())).thenReturn(1);

                var result = villageItemService.countItemsByVillage(villageName);

                var response = new VillageItemsCountResponseDto(1);
                assertThat(result).isEqualTo(response);
            }
        }
    }


}
