package unit.domains.item.service;

import com.depromeet.domains.item.dao.ItemPointDao;
import com.depromeet.domains.item.dto.request.NearItemPointRequestDto;
import com.depromeet.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.domains.item.dto.response.ItemsResponseDto;
import com.depromeet.domains.item.dto.response.PoiResponseDto;
import com.depromeet.domains.item.repository.ItemLikeRepository;
import com.depromeet.domains.item.repository.ItemLocationRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.item.service.ItemService;
import com.depromeet.domains.user.repository.BlockUserRepository;
import com.depromeet.item.Item;
import com.depromeet.item.ItemLocation;
import com.depromeet.music.album.Album;
import com.depromeet.music.album.AlbumCover;
import com.depromeet.music.artist.Artist;
import com.depromeet.music.genre.Genre;
import com.depromeet.music.genre.SongGenre;
import com.depromeet.music.song.Song;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import com.depromeet.util.GeomUtil;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
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

    @Mock
    ItemLikeRepository itemLikeRepository;

    @Mock
    BlockUserRepository blockUserRepository;


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
                User user = User.builder()
                        .nickname("test1")
                        .idfv("1234567")
                        .build();
                ReflectionTestUtils.setField(user, "id", 1L);
                NearItemPointRequestDto nearItemRequestDto = new NearItemPointRequestDto(127.123, 37.123, 1000.0, 1000.0);
                when(blockUserRepository.findBlockedIdsByBlockerId(any(Long.class))).thenReturn(List.of());
                when(itemLocationRepository.findNearItemsPointsByDistance(any(Point.class), any(Double.class), any(Double.class), anyList())).thenReturn(List.of());

                var result = itemService.findNearItemsPoints(user, nearItemRequestDto);

                assertThat(result).isEqualTo(new PoiResponseDto(List.of()));
            }

            @DisplayName("특정 지역 주변의 아이템 조회 - 조회 아이템이 2개 있는 경우")
            @Test
            void getNearItemPointsTestSuccess2() {
                User user = User.builder()
                        .nickname("test1")
                        .idfv("1234567")
                        .build();
                ReflectionTestUtils.setField(user, "id", 1L);

                NearItemPointRequestDto nearItemPointRequestDto = new NearItemPointRequestDto(127.123, 37.123, 1000.0, 1000.0);
                List<ItemPointDao> itemPointDaos = List.of(
                        new ItemPointDao(gf.createPoint(new Coordinate(127.123, 37.123)), 1L, "/image1.jpg", false),
                        new ItemPointDao(gf.createPoint(new Coordinate(127.133, 37.323)), 2L, "/image2.jpg", false)
                );

                when(itemLocationRepository.findNearItemsPointsByDistance(any(Point.class), anyDouble(), anyDouble(), anyList())).thenReturn(itemPointDaos);

                var result = itemService.findNearItemsPoints(user, nearItemPointRequestDto);

                assertThat(result).isEqualTo(
                        new PoiResponseDto(
                                List.of(
                                        new PoiResponseDto.PoiDto(1L, "/image1.jpg", 37.123, 127.123, false),
                                        new PoiResponseDto.PoiDto(2L, "/image2.jpg", 37.323, 127.133, false)
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
            void findNearItemsTestSuccess1() {
                User user = User.builder().build();
                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto(127.123, 37.123, 1000.0);

                when(itemRepository.findNearItemsByDistance(any(Point.class), any(Double.class))).thenReturn(List.of());

                var result = itemService.findNearItems(user, nearItemRequestDto);
                var expected = new ItemsResponseDto(List.of());

                assertThat(result).isEqualTo(expected);
            }

            @DisplayName("특정 지역 주변의 아이템 상세 조회 - 조회 아이템이 2개 있는 경우")
            @Test
            void findNearItemsTestSuccess2() {
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
                        .musicApp(MusicApp.APPLE_MUSIC)
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


                ReflectionTestUtils.setField(item1, "id", 1L);
                ReflectionTestUtils.setField(item2, "id", 2L);
                ReflectionTestUtils.setField(user, "id", 1L);

                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto(127.123, 37.123, 1000.0);
                assertThat(user.getId()).isEqualTo(1L);
                when(blockUserRepository.findBlockedIdsByBlockerId(anyLong())).thenReturn(List.of());
                when(itemRepository.findNearItemsByDistance(any(Point.class), any(Double.class))).thenReturn(List.of(item1, item2));
                when(itemLikeRepository.existsByUserIdAndItemId(anyLong(), anyLong())).thenReturn(true);

                var result = itemService.findNearItems(user, nearItemRequestDto);
                var expected = new ItemsResponseDto(
                        List.of(
                                new ItemsResponseDto.ItemDetailDto(item1, true),
                                new ItemsResponseDto.ItemDetailDto(item2, true)
                        )
                );
                assertThat(result).isEqualTo(expected);
            }
        }
    }
}
