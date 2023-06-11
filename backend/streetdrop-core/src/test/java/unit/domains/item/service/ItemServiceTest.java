package unit.domains.item.service;

import com.depromeet.domains.item.dao.ItemPointDao;
import com.depromeet.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.domains.item.dto.response.ItemsResponseDto;
import com.depromeet.domains.item.dto.response.PoiResponseDto;
import com.depromeet.domains.item.entity.Item;
import com.depromeet.domains.item.entity.ItemLocation;
import com.depromeet.domains.item.repository.ItemLocationRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.item.service.ItemService;
import com.depromeet.domains.music.album.entity.Album;
import com.depromeet.domains.music.album.entity.AlbumCover;
import com.depromeet.domains.music.artist.entity.Artist;
import com.depromeet.domains.music.genre.entity.Genre;
import com.depromeet.domains.music.genre.entity.SongGenre;
import com.depromeet.domains.music.song.entity.Song;
import com.depromeet.domains.user.entity.User;
import com.depromeet.common.util.GeomUtil;
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
    ItemRepository itemRepository;

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

                List<ItemPointDao> itemPointDaos = List.of(
                        new ItemPointDao(gf.createPoint(new Coordinate(127.123, 37.123)), 1L, "/image1.jpg"),
                        new ItemPointDao(gf.createPoint(new Coordinate(127.133, 37.323)), 2L, "/image2.jpg")
                );
                when(itemLocationRepository.findNearItemsPointsByDistance(any(Point.class), any(Double.class))).thenReturn(itemPointDaos);

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

    @DisplayName("특정 지역 주변의 아이템 상세 조회")
    @Nested
    class FindNearItemsTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("특정 지역 주변의 아이템 상세 조회 - 조회 아이템이 없는 경우")
            @Test
            void itemNotExist() {
                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto(127.123, 37.123, 1000.0);

                when(itemRepository.findNearItemsByDistance(any(Point.class), any(Double.class))).thenReturn(List.of());

                var result = itemService.findNearItems(nearItemRequestDto);
                var expected = new ItemsResponseDto(List.of());

                assertThat(result).isEqualTo(expected);
            }

            @DisplayName("특정 지역 주변의 아이템 상세 조회 - 조회 아이템이 2개 있는 경우")
            @Test
            void itemsExist() {
                ItemLocation itemLocation1 = ItemLocation.builder()
                        .name("Location1")
                        .point(GeomUtil.createPoint(37.123, 127.123))
                        .build();
                ItemLocation itemLocation2 = ItemLocation.builder()
                        .name("Location2")
                        .point(GeomUtil.createPoint(37.123, 127.123))
                        .build();
                User user = User.builder()
                        .nickname("user name")
                        .build();
                Artist artist = Artist.builder()
                        .name("artist name")
                        .build();
                Album album = Album.builder()
                        .name("album name")
                        .artist(artist)
                        .build();
                AlbumCover albumCover = AlbumCover.builder()
                        .albumImage("/image.jpg")
                        .build();
                Genre genre = Genre.builder()
                        .name("genre name")
                        .build();
                SongGenre songGenre = SongGenre.builder()
                        .genre(genre)
                        .build();
                Song song = Song.builder()
                        .name("song title")
                        .album(album)
                        .genres(List.of(songGenre))
                        .build();

                Item item1 = Item.builder()
                        .content("item1")
                        .itemLocation(itemLocation1)
                        .user(user)
                        .song(song)
                        .albumCover(albumCover)
                        .build();
                Item item2 = Item.builder()
                        .content("item2")
                        .itemLocation(itemLocation2)
                        .user(user)
                        .song(song)
                        .albumCover(albumCover)
                        .build();

                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto(127.123, 37.123, 1000.0);
                when(itemRepository.findNearItemsByDistance(any(Point.class), any(Double.class))).thenReturn(List.of(item1, item2));

                var result = itemService.findNearItems(nearItemRequestDto);
                var expected = new ItemsResponseDto(
                        List.of(
                            new ItemsResponseDto.ItemDetailDto(item1),
                            new ItemsResponseDto.ItemDetailDto(item2)
                        )
                );
                assertThat(result).isEqualTo(expected);
            }
        }
    }
}
