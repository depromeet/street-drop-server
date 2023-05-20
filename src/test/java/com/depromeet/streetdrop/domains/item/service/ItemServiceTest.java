package com.depromeet.streetdrop.domains.item.service;

import com.depromeet.streetdrop.domains.item.dao.ItemPoint;
import com.depromeet.streetdrop.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.response.PoiResponseDto;
import com.depromeet.streetdrop.domains.item.repository.ItemLocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] ItemService 테스트")
public class ItemServiceTest {

    @InjectMocks
    ItemService itemService;

    @Mock
    ItemLocationRepository itemLocationRepository;

    private final GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

    @DisplayName("특정 지역 주변의 아이템 조회")
    @Nested
    class GetNearItemPointsTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("특정 지역 주변의 아이템 조회 - 조회 아이템이 없는 경우")
            @Test
            void getNearItemPointsTestSuccess1() {
                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto(127.123, 37.123, 1000.0);
                when(itemLocationRepository.findNearItemsPointsByDistance(any(Point.class), any(Double.class))).thenReturn(List.of());

                var result = itemService.findNearItemsPoints(nearItemRequestDto);

                assertThat(result).isEqualTo(new PoiResponseDto(List.of()));
            }

            @DisplayName("특정 지역 주변의 아이템 조회 - 조회 아이템이 2개 있는 경우")
            @Test
            void getNearItemPointsTestSuccess2() {
                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto(127.123, 37.123, 1000.0);

                List<ItemPoint> itemPoints = List.of(
                        new ItemPoint(gf.createPoint(new Coordinate(127.123, 37.123)), 1L, "/image1.jpg"),
                        new ItemPoint(gf.createPoint(new Coordinate(127.133, 37.323)), 2L, "/image2.jpg")
                );
                when(itemLocationRepository.findNearItemsPointsByDistance(any(Point.class), any(Double.class))).thenReturn(itemPoints);

                var result = itemService.findNearItemsPoints(nearItemRequestDto);

                assertThat(result).isEqualTo(
                        new PoiResponseDto(
                                List.of(
                                        new PoiResponseDto.PoiDto(1L, "/image1.jpg", 37.123, 127.123),
                                        new PoiResponseDto.PoiDto(2L, "/image2.jpg", 37.323, 127.133)
                                )
                        )
                );
            }
        }
    }
}
