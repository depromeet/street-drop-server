package com.depromeet.domains.geo.controller;


import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.geo.dto.request.ReverseGeocodeRequestDto;
import com.depromeet.domains.geo.dto.response.ReverseGeocodeResponseDto;
import com.depromeet.domains.geo.service.GeoService;
import com.depromeet.external.swagger.annotation.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
@RequiredArgsConstructor
@Tag(name = "🔁Geo", description = "Geo Service API")
public class GeoController {

    private final GeoService geoService;

    @Operation(summary = "Reverse Geocoding")
    @ApiResponse(responseCode = "200", description = "좌표 주소 변환 성공")
    @ApiErrorResponse(errorCode = "GEO_NOT_SUPPORT_LOCATION", description = "유효하지 않은 좌표 입니다")
    @GetMapping("/reverse-geocode")
    public ResponseEntity<ReverseGeocodeResponseDto> reverseGeocode(
            @Valid ReverseGeocodeRequestDto reverseGeocodeRequestDto
    ) {
        var response = geoService.reverseGeocode(reverseGeocodeRequestDto);
        return ResponseDto.ok(response);
    }

}
