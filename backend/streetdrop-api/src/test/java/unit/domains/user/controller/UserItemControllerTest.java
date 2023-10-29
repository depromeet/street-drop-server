package unit.domains.user.controller;

import com.depromeet.domains.user.controller.UserItemController;
import com.depromeet.domains.user.dto.response.UserPoiResponseDto;
import com.depromeet.domains.user.service.UserItemService;
import com.depromeet.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import unit.annotation.MockAnonymousUser;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = UserItemController.class)
@WebMvcTest(controllers = {UserItemController.class})
@Import(UserItemController.class)
class UserItemControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserItemService userItemService;

    @DisplayName("[GET] 사용자가 드랍한 아이템 poi 조회")
    @Nested
    @MockAnonymousUser
    class GetUserDropItemPointsTest {
        @Nested
        @DisplayName("사용자가 드롭한 아이템 poi 조회 성공")
        class Success {
            @DisplayName("사용자가 드롭한 아이템 poi 조회 - 조회 아이템이 0개 있는 경우")
            @Test
            void getUserDropItemsZeroPointTest() throws Exception {
                UserPoiResponseDto poiResponseDto = new UserPoiResponseDto(List.of());

                given(userItemService.getDropItemsPoints(any(User.class))).willReturn(poiResponseDto);

                var response = mvc.perform(
                        get("/users/me/items/drop/points")
                                .header("x-sdp-idfv", "idfv1")
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.poi").isArray());
            }

            @DisplayName("사용자가 드롭한 아이템 poi 조회 - 조회 아이템이 1개 있는 경우")
            @Test
            void getUserDropItemsOnePointTest() throws Exception {
                UserPoiResponseDto.UserPoiDto userPoiResponseDto = new UserPoiResponseDto.UserPoiDto(1L, "/image1.jpg", 37.123454, 127.123456);
                UserPoiResponseDto poiResponseDto = new UserPoiResponseDto(List.of(userPoiResponseDto));

                given(userItemService.getDropItemsPoints(any(User.class))).willReturn(poiResponseDto);

                var response = mvc.perform(
                        get("/users/me/items/drop/points")
                                .header("x-sdp-idfv", "idfv1")
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.poi").isArray())
                        .andExpect(jsonPath("$.poi[0].itemId").value(1L))
                        .andExpect(jsonPath("$.poi[0].albumCover").value("/image1.jpg"))
                        .andExpect(jsonPath("$.poi[0].latitude").value(37.123454))
                        .andExpect(jsonPath("$.poi[0].longitude").value(127.123456));
            }

            @DisplayName("사용자가 드롭한 아이템 poi 조회 - 조회 아이템이 2개 있는 경우")
            @Test
            void getUserDropItemsTwoPointsTest() throws Exception {
                UserPoiResponseDto.UserPoiDto userPoiResponseDto1 = new UserPoiResponseDto.UserPoiDto(1L, "/image1.jpg", 37.123454, 127.123456);
                UserPoiResponseDto.UserPoiDto userPoiResponseDto2 = new UserPoiResponseDto.UserPoiDto(2L, "/image2.jpg", 37.123436, 127.123466);
                UserPoiResponseDto poiResponseDto = new UserPoiResponseDto(List.of(userPoiResponseDto1, userPoiResponseDto2));

                given(userItemService.getDropItemsPoints(any(User.class))).willReturn(poiResponseDto);

                var response = mvc.perform(
                        get("/users/me/items/drop/points")
                                .header("x-sdp-idfv", "idfv1")
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.poi").isArray())
                        .andExpect(jsonPath("$.poi[0].itemId").value(1L))
                        .andExpect(jsonPath("$.poi[0].albumCover").value("/image1.jpg"))
                        .andExpect(jsonPath("$.poi[0].latitude").value(37.123454))
                        .andExpect(jsonPath("$.poi[0].longitude").value(127.123456))
                        .andExpect(jsonPath("$.poi[1].itemId").value(2L))
                        .andExpect(jsonPath("$.poi[1].albumCover").value("/image2.jpg"))
                        .andExpect(jsonPath("$.poi[1].latitude").value(37.123436))
                        .andExpect(jsonPath("$.poi[1].longitude").value(127.123466));
            }
        }
    }

    @DisplayName("[GET] 사용자가 찜한 아이템 poi 조회")
    @Nested
    @MockAnonymousUser
    class GetUserLikedItemPointsTest {
        @Nested
        @DisplayName("사용자가 찜한 아이템 poi 조회 성공")
        class Success {
            @DisplayName("사용자가 찜한 아이템 poi 조회 - 조회 아이템이 0개 있는 경우")
            @Test
            void getUserDropItemsZeroPointTest() throws Exception {
                UserPoiResponseDto poiResponseDto = new UserPoiResponseDto(List.of());

                given(userItemService.getLikedItemsPoints(any(User.class))).willReturn(poiResponseDto);

                var response = mvc.perform(
                        get("/users/me/items/like/points")
                                .header("x-sdp-idfv", "idfv1")
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.poi").isArray());
            }

            @DisplayName("사용자가 찜한 아이템 poi 조회 - 조회 아이템이 1개 있는 경우")
            @Test
            void getUserDropItemsOnePointTest() throws Exception {
                UserPoiResponseDto.UserPoiDto userPoiResponseDto = new UserPoiResponseDto.UserPoiDto(1L, "/image1.jpg", 37.123454, 127.123456);
                UserPoiResponseDto poiResponseDto = new UserPoiResponseDto(List.of(userPoiResponseDto));

                given(userItemService.getLikedItemsPoints(any(User.class))).willReturn(poiResponseDto);

                var response = mvc.perform(
                        get("/users/me/items/like/points")
                                .header("x-sdp-idfv", "idfv1")
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.poi").isArray())
                        .andExpect(jsonPath("$.poi[0].itemId").value(1L))
                        .andExpect(jsonPath("$.poi[0].albumCover").value("/image1.jpg"))
                        .andExpect(jsonPath("$.poi[0].latitude").value(37.123454))
                        .andExpect(jsonPath("$.poi[0].longitude").value(127.123456));
            }

            @DisplayName("사용자가 찜한 아이템 poi 조회 - 조회 아이템이 2개 있는 경우")
            @Test
            void getUserDropItemsTwoPointsTest() throws Exception {
                UserPoiResponseDto.UserPoiDto userPoiResponseDto1 = new UserPoiResponseDto.UserPoiDto(1L, "/image1.jpg", 37.123454, 127.123456);
                UserPoiResponseDto.UserPoiDto userPoiResponseDto2 = new UserPoiResponseDto.UserPoiDto(2L, "/image2.jpg", 37.123436, 127.123466);
                UserPoiResponseDto poiResponseDto = new UserPoiResponseDto(List.of(userPoiResponseDto1, userPoiResponseDto2));

                given(userItemService.getLikedItemsPoints(any(User.class))).willReturn(poiResponseDto);

                var response = mvc.perform(
                        get("/users/me/items/like/points")
                                .header("x-sdp-idfv", "idfv1")
                );

                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.poi").isArray())
                        .andExpect(jsonPath("$.poi[0].itemId").value(1L))
                        .andExpect(jsonPath("$.poi[0].albumCover").value("/image1.jpg"))
                        .andExpect(jsonPath("$.poi[0].latitude").value(37.123454))
                        .andExpect(jsonPath("$.poi[0].longitude").value(127.123456))
                        .andExpect(jsonPath("$.poi[1].itemId").value(2L))
                        .andExpect(jsonPath("$.poi[1].albumCover").value("/image2.jpg"))
                        .andExpect(jsonPath("$.poi[1].latitude").value(37.123436))
                        .andExpect(jsonPath("$.poi[1].longitude").value(127.123466));
            }
        }
    }

}