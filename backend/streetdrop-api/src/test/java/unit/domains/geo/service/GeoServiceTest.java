package unit.domains.geo.service;

import com.depromeet.area.village.VillageArea;
import com.depromeet.domains.geo.dto.request.ReverseGeocodeRequestDto;
import com.depromeet.domains.geo.dto.response.ReverseGeocodeResponseDto;
import com.depromeet.domains.geo.service.GeoService;
import com.depromeet.domains.village.dto.response.VillageItemsCountResponseDto;
import com.depromeet.domains.village.repository.VillageAreaRepository;
import com.depromeet.domains.village.service.VillageAreaService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] GeoService 테스트")
public class GeoServiceTest {

    @InjectMocks
    GeoService geoService;

    @Mock
    VillageAreaService villageAreaService;

    @Spy
    VillageArea villageArea;


    @DisplayName("지역별 드랍 아이템 개수 조회")
    @Nested
    class VillageItemCountTest {

        private ReverseGeocodeRequestDto reverseGeocodeRequestDto;

        private ReverseGeocodeResponseDto reverseGeocodeResponseDto;
        @BeforeEach
        void setUp() throws NoSuchFieldException, IllegalAccessException {
            String villageName = "서울특별시 강남구";

            reverseGeocodeRequestDto = new ReverseGeocodeRequestDto(127.123, 37.123);

            reverseGeocodeResponseDto = new ReverseGeocodeResponseDto(villageName);

            Field villageNameField = VillageArea.class.getDeclaredField("villageName");
            villageNameField.setAccessible(true);
            villageNameField.set(villageArea, villageName);
        }
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("지역별 드랍 아이템 개수 조회")
            @Test
            void VillageItemCountTest() {
                when(villageAreaService.getVillageByLocationPoint(any())).thenReturn(villageArea);

                var result = geoService.reverseGeocode(reverseGeocodeRequestDto);

                assertThat(result).isEqualTo(reverseGeocodeResponseDto);
            }
        }
    }



}
