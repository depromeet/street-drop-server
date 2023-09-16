package unit.domains.geo.controller;

import com.depromeet.common.error.GlobalExceptionHandler;
import com.depromeet.domains.geo.controller.GeoController;
import com.depromeet.domains.geo.dto.request.ReverseGeocodeRequestDto;
import com.depromeet.domains.geo.dto.response.ReverseGeocodeResponseDto;
import com.depromeet.domains.geo.service.GeoService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = GeoController.class)
@WebMvcTest(controllers = {GeoController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import({GeoController.class, GlobalExceptionHandler.class})
@DisplayName("[API][Controller] GeoController 테스트")
public class GeoControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    GeoService geoService;


    @DisplayName("[Get] Reverse Geocoding Test")
    @Nested
    class ReverseGeocodingTest {

        double latitude;
        double longitude;

        @BeforeEach
        void setUp() {
            latitude = 37.123456;
            longitude = 127.123456;
        }

        @Nested
        @DisplayName("성공")
        class Success {

            @Test
            @DisplayName("Reverse Geocoding - 좌표 정상 반환 성공")
            void reverseGeocodingSuccess() throws Exception {
                // Given
                given(geoService.reverseGeocode(any(ReverseGeocodeRequestDto.class)))
                        .willReturn(new ReverseGeocodeResponseDto("서울특별시 성수동"));

                // When
                var response = mvc.perform(get("/geo/reverse-geocode")
                        .param("latitude", String.valueOf(latitude))
                        .param("longitude", String.valueOf(longitude))
                        .contentType(MediaType.APPLICATION_JSON));

                // Then
                response.andExpect(status().isOk())
                        .andExpect(jsonPath("$.villageName").value("서울특별시 성수동"));

            }

        }

        @Nested
        @DisplayName("아이템 드랍 실패")
        class Fail {

            @DisplayName("Latitude 유효성 검사 실패 - 값이 없는 경우")
            @Test
            void latitudeNotExist() throws Exception {

                var response = mvc.perform(
                        get("/geo/reverse-geocode")
                                .param("longitude", String.valueOf(longitude))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Latitude is required"));

            }

            @DisplayName("Latitude 유효성 검사 실패 - 범위에 맞지 않는 경우")
            @Test
            void latitudeOutOfRange() throws Exception {

                var response = mvc.perform(
                        get("/geo/reverse-geocode")
                                .param("latitude", String.valueOf(1000.0))
                                .param("longitude", String.valueOf(longitude))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Not valid latitude, must be between -90 and 90"));

            }

            @DisplayName("Longitude 유효성 검사 실패 - 값이 없는 경우")
            @Test
            void longitudeNotExist() throws Exception {

                var response = mvc.perform(
                        get("/geo/reverse-geocode")
                                .param("latitude", String.valueOf(latitude))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Longitude is required"));

            }

            @DisplayName("Longitude 유효성 검사 실패 - 범위에 맞지 않는 경우")
            @Test
            void longitudeOutOfRange() throws Exception {

                var response = mvc.perform(
                        get("/geo/reverse-geocode")
                                .param("latitude", String.valueOf(latitude))
                                .param("longitude", String.valueOf(1000.0))
                );

                response.andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Not valid longitude, must be between -180 and 180"));

            }

        }
    }
}
