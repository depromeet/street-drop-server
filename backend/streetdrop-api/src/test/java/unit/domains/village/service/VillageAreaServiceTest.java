package unit.domains.village.service;

import com.depromeet.area.village.VillageArea;
import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.domains.village.repository.VillageAreaRepository;
import com.depromeet.domains.village.service.VillageAreaService;
import com.depromeet.util.GeomUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Point;
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
@DisplayName("[Service] VillageAreaService 테스트")
public class VillageAreaServiceTest {

    @InjectMocks
    VillageAreaService villageAreaService;

    @Mock
    VillageAreaRepository villageAreaRepository;

    @Spy
    VillageArea villageArea;

    @DisplayName("지역 좌표를 통해서 지역명 조회")
    @Nested
    class getVillageByLocationPointTest {

        @Nested
        @DisplayName("성공")
        class Success {

            private Point correctPoint;

            private String villageName;

            @BeforeEach
            void setUp() throws NoSuchFieldException, IllegalAccessException {
                correctPoint = GeomUtil.createPoint(127.123, 37.123);
                villageName = "서울특별시 강남구";

                Field villageNameField = VillageArea.class.getDeclaredField("villageName");
                villageNameField.setAccessible(true);
                villageNameField.set(villageArea, villageName);
            }

            @DisplayName("지역 좌표를 통해서 지역명 조회 - 성공")
            @Test
            void getVillageByLocationPointSuccessTest() {
                when(villageAreaRepository.findVillageByLocationPoint(any())).thenReturn(Optional.of(villageArea));

                var result = villageAreaService.getVillageByLocationPoint(correctPoint);

                assertThat(result.getVillageName()).isEqualTo(villageName);
            }
        }

        @Nested
        @DisplayName("실패")
        class Fail {

            private Point invalidPoint;

            @BeforeEach
            void setUp() throws NoSuchFieldException, IllegalAccessException {
                invalidPoint = GeomUtil.createPoint(0.0, 0.0);
                String villageName = "서울특별시 강남구";

                Field villageNameField = VillageArea.class.getDeclaredField("villageName");
                villageNameField.setAccessible(true);
                villageNameField.set(villageArea, villageName);
            }

            @DisplayName("지역 좌표가 올바르지 않을 경우")
            @Test
            void VillageItemCountTest_NotSupportLocation() {
                when(villageAreaRepository.findVillageByLocationPoint(any())).thenReturn(Optional.empty());

                Throwable thrown = catchThrowable(() -> villageAreaService.getVillageByLocationPoint(invalidPoint));

                assertThat(thrown).isInstanceOf(RuntimeException.class)
                        .hasMessageContaining(ErrorCode.NOT_SUPPORT_LOCATION.getMessage());
            }
        }
    }

}
