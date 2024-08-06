package unit.domains.user.service;

import com.depromeet.domains.item.repository.ItemLikeRepository;
import com.depromeet.domains.item.repository.ItemLocationRepository;
import com.depromeet.domains.user.dao.UserItemPointDao;
import com.depromeet.domains.user.dto.response.UserItemLocationCountDto;
import com.depromeet.domains.user.dto.response.UserPoiResponseDto;
import com.depromeet.domains.user.service.UserItemService;
import com.depromeet.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] UserItemService 테스트")
class UserItemServiceTest {

    @InjectMocks
    UserItemService userItemService;

    @Mock
    ItemLocationRepository itemLocationRepository;

    @Mock
    ItemLikeRepository itemLikeRepository;

    private final GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

    @DisplayName("[GET] 사용자가 드랍한 아이템 poi 조회")
    @Nested
    class GetUserDropItemPointsTest {
        @Nested
        @DisplayName("사용자가 드롭한 아이템 poi 조회 성공")
        class Success {
            @DisplayName("사용자가 드롭한 아이템 poi 조회 - 조회 아이템이 없는 경우")
            @Test
            void getUserDropItemsZeroPointTest() {
                User user = User.builder().build();
                when(itemLocationRepository.findUserDropItemsPoints(user.getId())).thenReturn(List.of());

                var result = userItemService.getDropItemsPoints(user);
                var expected = new UserPoiResponseDto(List.of());

                assertThat(result).isEqualTo(expected);
            }

            @DisplayName("사용자가 드롭한 아이템 poi 조회 - 조회 아이템이 1개 있는 경우")
            @Test
            void getUserDropItemsOnePointTest() {
                User user = User.builder().build();
                ReflectionTestUtils.setField(user, "id", 1L);

                List<UserItemPointDao> userItemPointDaos = List.of(
                        new UserItemPointDao(gf.createPoint(new Coordinate(127.123, 37.123)), 1L, "/image1.jpg")
                );

                when(itemLocationRepository.findUserDropItemsPoints(user.getId())).thenReturn(userItemPointDaos);

                var result = userItemService.getDropItemsPoints(user);
                var expected = new UserPoiResponseDto(
                        List.of(
                                new UserPoiResponseDto.UserPoiDto(1L, "/image1.jpg", 37.123, 127.123)
                        )
                );

                assertThat(result).isEqualTo(expected);
            }

            @DisplayName("사용자가 드롭한 아이템 poi 조회 - 조회 아이템이 2개 있는 경우")
            @Test
            void getUserDropItemsTwoPointsTest() {
                User user = User.builder().build();
                ReflectionTestUtils.setField(user, "id", 1L);

                List<UserItemPointDao> userItemPointDaos = List.of(
                        new UserItemPointDao(gf.createPoint(new Coordinate(127.123, 37.123)), 1L, "/image1.jpg"),
                        new UserItemPointDao(gf.createPoint(new Coordinate(127.123, 37.123)), 2L, "/image2.jpg")
                );

                when(itemLocationRepository.findUserDropItemsPoints(user.getId())).thenReturn(userItemPointDaos);

                var result = userItemService.getDropItemsPoints(user);
                var expected = new UserPoiResponseDto(
                        List.of(
                                new UserPoiResponseDto.UserPoiDto(1L, "/image1.jpg", 37.123, 127.123),
                                new UserPoiResponseDto.UserPoiDto(2L, "/image2.jpg", 37.123, 127.123)
                        )
                );

                assertThat(result).isEqualTo(expected);
            }
        }
    }

    @DisplayName("[GET] 사용자가 찜한 아이템 poi 조회")
    @Nested
    class GetUserLikedItemPointsTest {
        @Nested
        @DisplayName("사용자가 찜한 아이템 poi 조회 성공")
        class Success {
            @DisplayName("사용자가 찜한 아이템 poi 조회 - 조회 아이템이 없는 경우")
            @Test
            void getUserLikedItemsZeroPointTest() {
                User user = User.builder().build();
                when(itemLikeRepository.findUserLikedItemsPoints(user.getId())).thenReturn(List.of());

                var result = userItemService.getLikedItemsPoints(user);
                var expected = new UserPoiResponseDto(List.of());

                assertThat(result).isEqualTo(expected);
            }

            @DisplayName("사용자가 찜한 아이템 poi 조회 - 조회 아이템이 1개 있는 경우")
            @Test
            void getUserLikedItemsOnePointTest() {
                User user = User.builder().build();
                ReflectionTestUtils.setField(user, "id", 1L);

                List<UserItemPointDao> userItemPointDaos = List.of(
                        new UserItemPointDao(gf.createPoint(new Coordinate(127.123, 37.123)), 1L, "/image1.jpg")
                );

                when(itemLikeRepository.findUserLikedItemsPoints(user.getId())).thenReturn(userItemPointDaos);

                var result = userItemService.getLikedItemsPoints(user);
                var expected = new UserPoiResponseDto(
                        List.of(
                                new UserPoiResponseDto.UserPoiDto(1L, "/image1.jpg", 37.123, 127.123)
                        )
                );

                assertThat(result).isEqualTo(expected);
            }

            @DisplayName("사용자가 찜한 아이템 poi 조회 - 조회 아이템이 2개 있는 경우")
            @Test
            void getUserLikedItemsTwoPointsTest() {
                User user = User.builder().build();
                ReflectionTestUtils.setField(user, "id", 1L);

                List<UserItemPointDao> userItemPointDaos = List.of(
                        new UserItemPointDao(gf.createPoint(new Coordinate(127.123, 37.123)), 1L, "/image1.jpg"),
                        new UserItemPointDao(gf.createPoint(new Coordinate(127.123, 37.123)), 2L, "/image2.jpg")
                );

                when(itemLikeRepository.findUserLikedItemsPoints(user.getId())).thenReturn(userItemPointDaos);

                var result = userItemService.getLikedItemsPoints(user);
                var expected = new UserPoiResponseDto(
                        List.of(
                                new UserPoiResponseDto.UserPoiDto(1L, "/image1.jpg", 37.123, 127.123),
                                new UserPoiResponseDto.UserPoiDto(2L, "/image2.jpg", 37.123, 127.123)
                        )
                );

                assertThat(result).isEqualTo(expected);
            }
        }
    }

    @Nested
    @DisplayName("사용자가 드랍한 아이템 개수 조회 성공")
    class Success {
        @DisplayName("사용자가 드랍한 아이템 개수 조회 - 시/군/구 필터링")
        @Test
        void getUserDropItemCountFilteredByCityTest() {
            String state = "서울";
            String city = "강남구";
            User user = User.builder().build();
            ReflectionTestUtils.setField(user, "id", 1L);
            when(itemLocationRepository.countItemsByCity(user.getId(), city)).thenReturn(2L);

            var result = userItemService.countItemsByLocation(user, state, city);
            var expected = new UserItemLocationCountDto(2L, state, city);

            assertThat(result).isEqualTo(expected);
        }

        @DisplayName("사용자가 드랍한 아이템 개수 조회 - 도/특별시/광역시 필터링")
        @Test
        void getUserDropItemCountFilteredByStateTest() {
            String state = "서울";
            String city = null;
            User user = User.builder().build();
            ReflectionTestUtils.setField(user, "id", 1L);
            when(itemLocationRepository.countItemsByState(user.getId(), state)).thenReturn(2L);

            var result = userItemService.countItemsByLocation(user, state, city);
            var expected = new UserItemLocationCountDto(2L, state, null);

            assertThat(result).isEqualTo(expected);
        }

        @DisplayName("사용자가 드랍한 아이템 개수 전체 조회 - 조회 아이템이 2개 있는 경우")
        @Test
        void getUserDropItemCountWithNoFilterTest() {
            String state = null;
            String city = null;
            User user = User.builder().build();
            ReflectionTestUtils.setField(user, "id", 1L);
            when(itemLocationRepository.countItems(user.getId())).thenReturn(2L);

            var result = userItemService.countItemsByLocation(user, state, city);
            var expected = new UserItemLocationCountDto(2L, null, null);

            assertThat(result).isEqualTo(expected);
        }
    }
}
