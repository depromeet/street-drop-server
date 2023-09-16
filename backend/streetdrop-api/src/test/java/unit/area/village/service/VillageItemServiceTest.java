package unit.area.village.service;

import com.depromeet.area.village.VillageArea;
import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.domains.village.dto.request.VillageItemsRequestDto;
import com.depromeet.domains.village.dto.response.VillageItemsCountResponseDto;
import com.depromeet.domains.village.repository.VillageAreaRepository;
import com.depromeet.domains.village.service.VillageItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] VillageItemService 테스트")
public class VillageItemServiceTest {

    @InjectMocks
    VillageItemService villageItemService;

    @Mock
    VillageAreaRepository villageAreaRepository;

    @Spy
    VillageArea villageArea;

    @DisplayName("지역 이름으로 드랍 아이템 개수 조회")
    @Nested
    class countItemsByVillageNameTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("지역 이름으로 드랍 아이템 개수 조회")
            @Test
            void VillageItemCountTest() {
                var villageName = "서울특별시 강남구";
                when(villageAreaRepository.countItemsByVillageName(any())).thenReturn(1);

                var result = villageItemService.countItemsByVillage(villageName);

                var response = new VillageItemsCountResponseDto(1, villageName);
                assertThat(result).isEqualTo(response);
            }

        }
    }


    @DisplayName("지역 좌표로 드랍 아이템 개수 조회")
    @Nested
    class countItemsInVillageByLocationTest {
        private double longitude;

        private double latitude;

        private String villageName;

        @BeforeEach
        void setUp() throws NoSuchFieldException, IllegalAccessException {
            longitude = 127.123456;
            latitude = 37.123456;
            villageName = "서울특별시 강남구";

            Field villageNameField = VillageArea.class.getDeclaredField("villageName");
            villageNameField.setAccessible(true);
            villageNameField.set(villageArea, villageName);
        }

        @Nested
        @DisplayName("성공")
        class Success {

            @DisplayName("지역 좌표로 드랍 아이템 개수 조회 - 성공")
            @Test
            void VillageItemCountTest() {
                // given
                when(villageAreaRepository.findVillageByLocationPoint(any())).thenReturn(Optional.of(villageArea));
                when(villageAreaRepository.countItemsByVillageName(any())).thenReturn(1);
                VillageItemsRequestDto villageItemsRequestDto = new VillageItemsRequestDto(longitude, latitude);

                // when
                var result = villageItemService.countItemsInVillageByLocation(villageItemsRequestDto);

                // then
                var response = new VillageItemsCountResponseDto(1, villageName);
                assertThat(result).isEqualTo(response);
            }

        }

        @Nested
        @DisplayName("실패")
        class Fail {

            @DisplayName("지역 좌표로 드랍 아이템 개수 조회 - 실패 - 지역이 존재하지 않음")
            @Test
            void VillageItemCountTest() {
                // given
                when(villageAreaRepository.findVillageByLocationPoint(any())).thenReturn(Optional.empty());
                VillageItemsRequestDto villageItemsRequestDto = new VillageItemsRequestDto(longitude, latitude);

                // when
                Throwable thrown = catchThrowable(() -> villageItemService.countItemsInVillageByLocation(villageItemsRequestDto));

                // then
                assertThat(thrown).isInstanceOf(RuntimeException.class)
                        .hasMessageContaining(ErrorCode.NOT_SUPPORT_LOCATION.getMessage());
            }

        }
    }


}
