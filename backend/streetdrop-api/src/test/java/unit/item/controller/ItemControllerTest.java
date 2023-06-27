package unit.item.controller;

import com.depromeet.domains.item.controller.ItemController;
import com.depromeet.common.error.GlobalExceptionHandler;
import com.depromeet.domains.item.dto.request.ItemCreateRequestDto;
import com.depromeet.domains.item.dto.request.ItemLocationRequestDto;
import com.depromeet.domains.item.dto.request.NearItemPointRequestDto;
import com.depromeet.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.domains.item.dto.response.ItemLocationResponseDto;
import com.depromeet.domains.item.dto.response.ItemResponseDto;
import com.depromeet.domains.item.dto.response.ItemsResponseDto;
import com.depromeet.domains.item.dto.response.PoiResponseDto;
import com.depromeet.domains.item.service.ItemLikeService;
import com.depromeet.domains.item.service.ItemService;
import com.depromeet.domains.music.dto.request.MusicRequestDto;
import com.depromeet.domains.music.dto.response.MusicResponseDto;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = ItemController.class)
@WebMvcTest(controllers = {ItemController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import({ItemController.class, GlobalExceptionHandler.class})
@DisplayName("[API][Controller] ItemController 테스트")
public class ItemControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ItemService itemService;

    @MockBean
    ItemLikeService itemLikeService;

    @DisplayName("[POST] 아이템 드랍 저장")
    @Nested
    class CreateItemDropTest {
        @Mock
        private User mockUser;

        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("아이템 드랍 성공")
            @Test
            void createItem_ValidRequest_ReturnsCreated() throws Exception {
                MusicRequestDto musicRequestDto = new MusicRequestDto("Love Dive", "IVE", "1st EP IVE", "https://www.youtube.com/watch?v=YGieI3KoeZk", List.of("K-POP", "HipHop"));
                ItemLocationRequestDto itemLocationRequestDto = new ItemLocationRequestDto(37.123456, 127.123456, "서울시 성수동 성수 1가");
                ItemCreateRequestDto itemRequestDto = new ItemCreateRequestDto(itemLocationRequestDto, musicRequestDto, "블라블라");
                ItemResponseDto itemResponseDto = createValidItemResponseDto();

                given(itemService.create(mockUser, itemRequestDto)).willReturn(itemResponseDto);

                var response = mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequestDto)));
                response.andExpect(status().isCreated());
            }
        }

        @Nested
        @DisplayName("아이템 드랍 실패")
        class Fail {
            @DisplayName("음악 유효성 검사 실패 - 음악 제목이 없는 경우")
            @Test
            void createItem_InvalidMusicTitle_ReturnsBadRequest() throws Exception {
                MusicRequestDto musicRequestDto = new MusicRequestDto(null, "IVE", "1st EP IVE", "https://www.youtube.com/watch?v=YGieI3KoeZk", List.of("K-POP", "HipHop"));
                ItemLocationRequestDto itemLocationRequestDto = new ItemLocationRequestDto(37.123456, 127.123456, "서울시 성수동 성수 1가");
                ItemCreateRequestDto itemRequestDto = new ItemCreateRequestDto(itemLocationRequestDto, musicRequestDto, "블라블라");
                ItemResponseDto itemResponseDto = createValidItemResponseDto();

                given(itemService.create(mockUser, itemRequestDto)).willReturn(itemResponseDto);

                var response = mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequestDto)));

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Title is required"));
            }

            @DisplayName("음악 유효성 검사 실패 - 아티스트가 없는 경우")
            @Test
            void createItem_InvalidMusicArtist_ReturnsBadRequest() throws Exception {
                MusicRequestDto musicRequestDto = new MusicRequestDto("Love Dive", null, "1st EP IVE", "https://www.youtube.com/watch?v=YGieI3KoeZk", List.of("K-POP", "HipHop"));
                ItemLocationRequestDto itemLocationRequestDto = new ItemLocationRequestDto(37.123456, 127.123456, "서울시 성수동 성수 1가");
                ItemCreateRequestDto itemRequestDto = new ItemCreateRequestDto(itemLocationRequestDto, musicRequestDto, "블라블라");
                ItemResponseDto itemResponseDto = createValidItemResponseDto();

                given(itemService.create(mockUser, itemRequestDto)).willReturn(itemResponseDto);

                var response = mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequestDto)));

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Artist is required"));
            }

            @DisplayName("음악 유효성 검사 실패 - 앨범명이 없는 경우")
            @Test
            void createItem_InvalidMusicAlbum_ReturnsBadRequest() throws Exception {
                MusicRequestDto musicRequestDto = new MusicRequestDto("Love Dive", "IVE", null, "https://www.youtube.com/watch?v=YGieI3KoeZk", List.of("K-POP", "HipHop"));
                ItemLocationRequestDto itemLocationRequestDto = new ItemLocationRequestDto(37.123456, 127.123456, "서울시 성수동 성수 1가");
                ItemCreateRequestDto itemRequestDto = new ItemCreateRequestDto(itemLocationRequestDto, musicRequestDto, "블라블라");
                ItemResponseDto itemResponseDto = createValidItemResponseDto();

                given(itemService.create(mockUser, itemRequestDto)).willReturn(itemResponseDto);

                var response = mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequestDto)));

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Album name is required"));
            }

            @DisplayName("음악 유효성 검사 실패 - 앨범 커버 이미지가 없는 경우")
            @Test
            void createItem_InvalidAlbumCover_ReturnsBadRequest() throws Exception {
                MusicRequestDto musicRequestDto = new MusicRequestDto("Love Dive", "IVE", "아이브 정규1집", null, List.of("K-POP", "HipHop"));
                ItemLocationRequestDto itemLocationRequestDto = new ItemLocationRequestDto(37.123456, 127.123456, "서울시 성수동 성수 1가");
                ItemCreateRequestDto itemRequestDto = new ItemCreateRequestDto(itemLocationRequestDto, musicRequestDto, "블라블라");
                ItemResponseDto itemResponseDto = createValidItemResponseDto();

                given(itemService.create(mockUser, itemRequestDto)).willReturn(itemResponseDto);

                var response = mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequestDto)));

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Album image is required"));
            }

            @DisplayName("위치 유효성 검사 실패 - latitude 값이 범위에 맞지 않는 경우")
            @Test
            void createItem_InvalidLatitudeRequest_ReturnsBadRequest() throws Exception {
                MusicRequestDto musicRequestDto = new MusicRequestDto("Love Dive", "IVE", "1st EP IVE", "https://www.youtube.com/watch?v=YGieI3KoeZk", List.of("K-POP", "HipHop"));
                ItemLocationRequestDto itemLocationRequestDto = new ItemLocationRequestDto(100000000.0, 127.123456, "서울시 성수동 성수 1가");
                ItemCreateRequestDto itemRequestDto = new ItemCreateRequestDto(itemLocationRequestDto, musicRequestDto, "블라블라");
                ItemResponseDto itemResponseDto = createValidItemResponseDto();

                given(itemService.create(mockUser, itemRequestDto)).willReturn(itemResponseDto);

                var response = mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequestDto)));

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Not valid latitude, must be between -90 and 90"));
            }

            @DisplayName("위치 유효성 검사 실패 - longitude 값이 범위에 맞지 않는 경우")
            @Test
            void createItem_InvalidLogitudeRequest_ReturnsBadRequest() throws Exception {
                MusicRequestDto musicRequestDto = new MusicRequestDto("Love Dive", "IVE", "1st EP IVE", "https://www.youtube.com/watch?v=YGieI3KoeZk", List.of("K-POP", "HipHop"));
                ItemLocationRequestDto itemLocationRequestDto = new ItemLocationRequestDto(37.123456, 100000000.0, "서울시 성수동 성수 1가");
                ItemCreateRequestDto itemRequestDto = new ItemCreateRequestDto(itemLocationRequestDto, musicRequestDto, "블라블라");
                ItemResponseDto itemResponseDto = createValidItemResponseDto();

                given(itemService.create(mockUser, itemRequestDto)).willReturn(itemResponseDto);

                var response = mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequestDto)));

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Not valid longitude, must be between -180 and 180"));
            }

            @DisplayName("위치 유효성 검사 실패 - 주소명이 없는 경우")
            @Test
            void createItem_InvalidAddressRequest_ReturnsBadRequest() throws Exception {
                MusicRequestDto musicRequestDto = new MusicRequestDto("Love Dive", "IVE", "1st EP IVE", "https://www.youtube.com/watch?v=YGieI3KoeZk", List.of("K-POP", "HipHop"));
                ItemLocationRequestDto itemLocationRequestDto = new ItemLocationRequestDto(37.123456, 127.123456, null);
                ItemCreateRequestDto itemRequestDto = new ItemCreateRequestDto(itemLocationRequestDto, musicRequestDto, "블라블라");
                ItemResponseDto itemResponseDto = createValidItemResponseDto();

                given(itemService.create(mockUser, itemRequestDto)).willReturn(itemResponseDto);

                var response = mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequestDto)));

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Address is required"));
            }

            @DisplayName("컨텐츠 유효성 검사 실패 - 컨텐츠가 없는 경우")
            @Test
            void createItem_InvalidContentRequest_ReturnsBadRequest() throws Exception {
                MusicRequestDto musicRequestDto = new MusicRequestDto("Love Dive", "IVE", "1st EP IVE", "https://www.youtube.com/watch?v=YGieI3KoeZk", List.of("K-POP", "HipHop"));
                ItemLocationRequestDto itemLocationRequestDto = new ItemLocationRequestDto(37.123456, 127.123456, "서울시 성수동 성수 1가");
                ItemCreateRequestDto itemRequestDto = new ItemCreateRequestDto(itemLocationRequestDto, musicRequestDto, null);
                ItemResponseDto itemResponseDto = createValidItemResponseDto();

                given(itemService.create(mockUser, itemRequestDto)).willReturn(itemResponseDto);

                var response = mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequestDto)));

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Content is required"));
            }
        }

        private ItemResponseDto createValidItemResponseDto() {
            UserResponseDto userResponse = new UserResponseDto("User1", "https://s3.orbi.kr/data/file/united/35546557a06831597f6e7851cb6c86e9.jpg", "youtubemusic");
            ItemLocationResponseDto locationResponse = new ItemLocationResponseDto("서울시 성수동 성수 1가");
            ItemResponseDto itemResponseDto = new ItemResponseDto(1L, userResponse, locationResponse, LocalDateTime.now(), 1);
            return itemResponseDto;
        }
    }

    @DisplayName("[GET] 내 주변 드랍 아이템 poi 조회")
    @Nested
    class GetNearItemPointsTest {
        @Nested
        @DisplayName("지역별 드랍 아이템 개수 조회 성공")
        class Success {
            double latitude;
            double longitude;

            @BeforeEach
            void setUp() {
                latitude = 37.123456;
                longitude = 127.123456;
            }

            @DisplayName("지역별 드랍 아이템 개수 조회 성공 - 2개 조회 성공, 거리 조회 X")
            @Test
            void getNearItemPointsTest() throws Exception {
                PoiResponseDto.PoiDto poiResponseDto1 = new PoiResponseDto.PoiDto(1L, "/butter1.jpg", 37.123454, 127.123456, false);
                PoiResponseDto.PoiDto poiResponseDto2 = new PoiResponseDto.PoiDto(2L, "/karina2.jpg", 37.123436, 127.123466, false);
                PoiResponseDto poiResponseDto = new PoiResponseDto(List.of(poiResponseDto1, poiResponseDto2));

                given(itemService.findNearItemsPoints(any(NearItemPointRequestDto.class))).willReturn(poiResponseDto);

                var response = mvc.perform(
                        get("/items/points")
                                .param("latitude", String.valueOf(latitude))
                                .param("longitude", String.valueOf(longitude))
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.poi").isArray())
                        .andExpect(jsonPath("$.poi[0].itemId").value(1L))
                        .andExpect(jsonPath("$.poi[0].albumCover").value("/butter1.jpg"))
                        .andExpect(jsonPath("$.poi[0].latitude").value(37.123454))
                        .andExpect(jsonPath("$.poi[0].longitude").value(127.123456))
                        .andExpect(jsonPath("$.poi[0].isInnerDistance").value(false))
                        .andExpect(jsonPath("$.poi[1].itemId").value(2L))
                        .andExpect(jsonPath("$.poi[1].albumCover").value("/karina2.jpg"))
                        .andExpect(jsonPath("$.poi[1].latitude").value(37.123436))
                        .andExpect(jsonPath("$.poi[1].longitude").value(127.123466))
                        .andExpect(jsonPath("$.poi[1].isInnerDistance").value(false));

            }

            @DisplayName("지역별 드랍 아이템 개수 조회 성공 - 0개 조회 성공")
            @Test
            void getNearItemPointsTest2() throws Exception {
                PoiResponseDto poiResponseDto = new PoiResponseDto(List.of());
                given(itemService.findNearItemsPoints(any(NearItemPointRequestDto.class))).willReturn(poiResponseDto);

                var response = mvc.perform(
                        get("/items/points")
                                .param("latitude", String.valueOf(latitude))
                                .param("longitude", String.valueOf(longitude))
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.poi").isArray())
                        .andExpect(jsonPath("$.poi").isEmpty());

            }

            @DisplayName("지역별 드랍 아이템 개수 조회 성공 - 1개 조회 성공, 범위 지정")
            @Test
            void getNearItemPointsTest3() throws Exception {
                PoiResponseDto.PoiDto poiResponseDto1 = new PoiResponseDto.PoiDto(1L, "/butter1.jpg", 37.123454, 127.123456, true);
                PoiResponseDto poiResponseDto = new PoiResponseDto(List.of(poiResponseDto1));

                given(itemService.findNearItemsPoints(any(NearItemPointRequestDto.class))).willReturn(poiResponseDto);

                var response = mvc.perform(
                        get("/items/points")
                                .param("latitude", String.valueOf(latitude))
                                .param("longitude", String.valueOf(longitude))
                                .param("distance", String.valueOf(1000))
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.poi").isArray())
                        .andExpect(jsonPath("$.poi[0].itemId").value(1L))
                        .andExpect(jsonPath("$.poi[0].albumCover").value("/butter1.jpg"))
                        .andExpect(jsonPath("$.poi[0].latitude").value(37.123454))
                        .andExpect(jsonPath("$.poi[0].longitude").value(127.123456))
                        .andExpect(jsonPath("$.poi[0].isInnerDistance").value(true));

            }

        }

        @Nested
        @DisplayName("지역별 드랍 아이템 개수 조회 실패")
        class Fail {

            @DisplayName("Latitude 유효성 검사 실패 - 값이 없는 경우")
            @Test
            void getNearItemPointsFail1() throws Exception {

                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto();
                nearItemRequestDto.setLongitude(127.123456);

                var response = mvc.perform(
                        get("/items/points")
                                .param("longitude", String.valueOf(nearItemRequestDto.getLongitude()))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Latitude is required"));

            }

            @DisplayName("Latitude 유효성 검사 실패 - 범위에 맞지 않는 경우")
            @Test
            void getNearItemPointsFail2() throws Exception {

                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto();
                nearItemRequestDto.setLatitude(1000.0);
                nearItemRequestDto.setLongitude(127.123456);


                var response = mvc.perform(
                        get("/items/points")
                                .param("latitude", String.valueOf(nearItemRequestDto.getLatitude()))
                                .param("longitude", String.valueOf(nearItemRequestDto.getLongitude()))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Not valid latitude, must be between -90 and 90"));

            }

            @DisplayName("Longitude 유효성 검사 실패  - 값이 없는 경우")
            @Test
            void getNearItemPointsFail3() throws Exception {

                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto();
                nearItemRequestDto.setLatitude(37.123456);

                var response = mvc.perform(
                        get("/items/points")
                                .param("latitude", String.valueOf(nearItemRequestDto.getLatitude()))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Longitude is required"));

            }

            @DisplayName("Longitude 유효성 검사 실패 - 범위에 맞지 않는 경우")
            @Test
            void getNearItemPointsFail4() throws Exception {

                NearItemRequestDto nearItemRequestDto = new NearItemRequestDto();
                nearItemRequestDto.setLatitude(37.123456);
                nearItemRequestDto.setLongitude(1000.0);

                var response = mvc.perform(
                        get("/items/points")
                                .param("latitude", String.valueOf(nearItemRequestDto.getLatitude()))
                                .param("longitude", String.valueOf(nearItemRequestDto.getLongitude()))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Not valid longitude, must be between -180 and 180"));

            }
        }
    }

    @DisplayName("[GET] 주변 아이템 상세 조회")
    @Nested
    class FindNearItemsTest {
        @Nested
        @DisplayName("특정 지역 주변의 아이템 상세 조회 성공")
        class Success {
            double latitude;
            double longitude;
            double distance;

            @BeforeEach
            void setUp() {
                latitude = 37.123456;
                longitude = 127.123456;
                distance = 1000.0;
            }

            @DisplayName("특정 지역 주변의 아이템 상세 조회 성공 - 1개 조회 성공, 거리 조회 X")
            @Test
            void findNearItemsTestSuccess1() throws Exception {
                ItemsResponseDto itemsResponseDto = new ItemsResponseDto(
                        List.of(
                                new ItemsResponseDto.ItemDetailDto(
                                        1L,
                                        new UserResponseDto("nickname", "/profile.jpg", "youtubemusic"),
                                        new ItemLocationResponseDto("성동구 성수1가 1동"),
                                        new MusicResponseDto("title", "artist", "/albumImage.jpg", List.of("genre")),
                                        "사용자 코멘트",
                                        LocalDateTime.of(2023, 5, 26, 12, 0),
                                        false,
                                        1
                                )
                        )

                );

                given(itemService.findNearItems(any(NearItemRequestDto.class))).willReturn(itemsResponseDto);

                var response = mvc.perform(
                        get("/items")
                                .param("latitude", String.valueOf(latitude))
                                .param("longitude", String.valueOf(longitude))
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.items[0].itemId").value(1L))
                        .andExpect(jsonPath("$.items[0].user.nickname").value("nickname"))
                        .andExpect(jsonPath("$.items[0].user.profileImage").value("/profile.jpg"))
                        .andExpect(jsonPath("$.items[0].user.musicApp").value("youtubemusic"))
                        .andExpect(jsonPath("$.items[0].location.address").value("성동구 성수1가 1동"))
                        .andExpect(jsonPath("$.items[0].music.title").value("title"))
                        .andExpect(jsonPath("$.items[0].music.artist").value("artist"))
                        .andExpect(jsonPath("$.items[0].music.albumImage").value("/albumImage.jpg"))
                        .andExpect(jsonPath("$.items[0].music.genre[0]").value("genre"))
                        .andExpect(jsonPath("$.items[0].content").value("사용자 코멘트"))
                        .andExpect(jsonPath("$.items[0].createdAt").value("2023-05-26 12:00:00"));

            }

            @DisplayName("특정 지역 주변의 아이템 상세 조회 성공 - 1개 조회 성공, 범위 지정")
            @Test
            void findNearItemsTestSuccess2() throws Exception {
                ItemsResponseDto itemsResponseDto = new ItemsResponseDto(
                        List.of(
                                new ItemsResponseDto.ItemDetailDto(
                                        1L,
                                        new UserResponseDto("nickname", "/profile.jpg", "youtubemusic"),
                                        new ItemLocationResponseDto("성동구 성수1가 1동"),
                                        new MusicResponseDto("title", "artist", "/albumImage.jpg", List.of("genre")),
                                        "사용자 코멘트",
                                        LocalDateTime.of(2023, 5, 26, 12, 0),
                                        false,
                                        1
                                )
                        )

                );

                given(itemService.findNearItems(any(NearItemRequestDto.class))).willReturn(itemsResponseDto);

                var response = mvc.perform(
                        get("/items")
                                .param("latitude", String.valueOf(latitude))
                                .param("longitude", String.valueOf(longitude))
                                .param("distance", String.valueOf(distance))
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.items[0].itemId").value(1L))
                        .andExpect(jsonPath("$.items[0].user.nickname").value("nickname"))
                        .andExpect(jsonPath("$.items[0].user.profileImage").value("/profile.jpg"))
                        .andExpect(jsonPath("$.items[0].user.musicApp").value("youtubemusic"))
                        .andExpect(jsonPath("$.items[0].location.address").value("성동구 성수1가 1동"))
                        .andExpect(jsonPath("$.items[0].music.title").value("title"))
                        .andExpect(jsonPath("$.items[0].music.artist").value("artist"))
                        .andExpect(jsonPath("$.items[0].music.albumImage").value("/albumImage.jpg"))
                        .andExpect(jsonPath("$.items[0].music.genre[0]").value("genre"))
                        .andExpect(jsonPath("$.items[0].content").value("사용자 코멘트"))
                        .andExpect(jsonPath("$.items[0].createdAt").value("2023-05-26 12:00:00"));

            }

            @Nested
            @DisplayName("특정 지역 주변의 아이템 상세 조회 실패")
            class Fail {

                @DisplayName("Latitude 유효성 검사 실패 - 값이 없는 경우")
                @Test
                void findNearItemsTestFail1() throws Exception {

                    NearItemRequestDto nearItemRequestDto = new NearItemRequestDto();
                    nearItemRequestDto.setLongitude(127.123456);

                    var response = mvc.perform(
                            get("/items")
                                    .param("longitude", String.valueOf(nearItemRequestDto.getLongitude()))
                    );

                    response.andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message").value("Latitude is required"));

                }

                @DisplayName("Latitude 유효성 검사 실패 - 범위에 맞지 않는 경우")
                @Test
                void findNearItemsTestFail2() throws Exception {

                    NearItemRequestDto nearItemRequestDto = new NearItemRequestDto();
                    nearItemRequestDto.setLatitude(1000.0);
                    nearItemRequestDto.setLongitude(127.123456);


                    var response = mvc.perform(
                            get("/items")
                                    .param("latitude", String.valueOf(nearItemRequestDto.getLatitude()))
                                    .param("longitude", String.valueOf(nearItemRequestDto.getLongitude()))
                    );

                    response.andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message").value("Not valid latitude, must be between -90 and 90"));

                }

                @DisplayName("Longitude 유효성 검사 실패  - 값이 없는 경우")
                @Test
                void findNearItemsTestFail3() throws Exception {

                    NearItemRequestDto nearItemRequestDto = new NearItemRequestDto();
                    nearItemRequestDto.setLatitude(37.123456);

                    var response = mvc.perform(
                            get("/items")
                                    .param("latitude", String.valueOf(nearItemRequestDto.getLatitude()))
                    );

                    response.andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message").value("Longitude is required"));

                }

                @DisplayName("Longitude 유효성 검사 실패 - 범위에 맞지 않는 경우")
                @Test
                void findNearItemsTestFail4() throws Exception {

                    NearItemRequestDto nearItemRequestDto = new NearItemRequestDto();
                    nearItemRequestDto.setLatitude(37.123456);
                    nearItemRequestDto.setLongitude(1000.0);

                    var response = mvc.perform(
                            get("/items")
                                    .param("latitude", String.valueOf(nearItemRequestDto.getLatitude()))
                                    .param("longitude", String.valueOf(nearItemRequestDto.getLongitude()))
                    );

                    response.andExpect(status().isBadRequest())
                            .andExpect(jsonPath("$.message").value("Not valid longitude, must be between -180 and 180"));

                }
            }
        }
    }
}
