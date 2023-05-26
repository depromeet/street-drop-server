package com.depromeet.streetdrop.apis.item.controller;

import com.depromeet.streetdrop.domains.item.dto.request.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.PoiResponseDto;
import com.depromeet.streetdrop.domains.item.service.ItemService;
import com.depromeet.streetdrop.domains.itemLocation.dto.request.LocationRequestDto;
import com.depromeet.streetdrop.domains.itemLocation.dto.response.LocationResponseDto;
import com.depromeet.streetdrop.domains.music.dto.request.MusicRequestDto;
import com.depromeet.streetdrop.domains.user.dto.response.UserResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ItemController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import(ItemController.class)
@DisplayName("[API][Controller] ItemController 테스트")
public class ItemControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ItemService itemService;

    @DisplayName("[POST] 아이템 드랍 저장")
    @Nested
    class CreateItemDropTest {
        @Nested
        @DisplayName("성공")
        class Success {
            @DisplayName("아이템 드랍 저장 성공")
            @Test
            void createTest() throws Exception {
                List<String> genres = new ArrayList<>();
                genres.add("K-pop");

                MusicRequestDto music = new MusicRequestDto("Love Dive", "IVE", "1st EP IVE", "https://www.youtube.com/watch?v=YGieI3KoeZk", genres);
                LocationRequestDto location = new LocationRequestDto(37.123456, 127.123456, "서울시 성수동 성수 1가");
                ItemRequestDto itemRequest = new ItemRequestDto(location, music, "블라블라");

                UserResponseDto userResponse = new UserResponseDto("User1", "https://s3.orbi.kr/data/file/united/35546557a06831597f6e7851cb6c86e9.jpg", "youtubemusic");
                LocationResponseDto locationResponse = new LocationResponseDto("서울시 성수동 성수 1가");
                ItemResponseDto itemResponse = new ItemResponseDto(1L, userResponse, locationResponse, LocalDateTime.now());

                given(itemService.create(any(ItemRequestDto.class))).willReturn(itemResponse);
                mvc.perform(
                        post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemRequest)))
                        .andExpect(status().isCreated());
            }
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
                PoiResponseDto.PoiDto poiResponseDto1 = new PoiResponseDto.PoiDto(1L, "/butter1.jpg", 37.123454, 127.123456);
                PoiResponseDto.PoiDto poiResponseDto2 = new PoiResponseDto.PoiDto(2L, "/karina2.jpg", 37.123436, 127.123466);
                PoiResponseDto poiResponseDto = new PoiResponseDto(List.of(poiResponseDto1, poiResponseDto2));

                given(itemService.findNearItemsPoints(any(NearItemRequestDto.class))).willReturn(poiResponseDto);

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
                        .andExpect(jsonPath("$.poi[1].itemId").value(2L))
                        .andExpect(jsonPath("$.poi[1].albumCover").value("/karina2.jpg"))
                        .andExpect(jsonPath("$.poi[1].latitude").value(37.123436))
                        .andExpect(jsonPath("$.poi[1].longitude").value(127.123466));

            }

            @DisplayName("지역별 드랍 아이템 개수 조회 성공 - 0개 조회 성공")
            @Test
            void getNearItemPointsTest2() throws Exception {
                PoiResponseDto poiResponseDto = new PoiResponseDto(List.of());
                given(itemService.findNearItemsPoints(any(NearItemRequestDto.class))).willReturn(poiResponseDto);

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
                PoiResponseDto.PoiDto poiResponseDto1 = new PoiResponseDto.PoiDto(1L, "/butter1.jpg", 37.123454, 127.123456);
                PoiResponseDto poiResponseDto = new PoiResponseDto(List.of(poiResponseDto1));

                given(itemService.findNearItemsPoints(any(NearItemRequestDto.class))).willReturn(poiResponseDto);

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
                        .andExpect(jsonPath("$.poi[0].longitude").value(127.123456));

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
}
