package com.depromeet.streetdrop.domains.itemLocation.dto.request;

import com.depromeet.streetdrop.domains.common.annotation.IsLatitude;
import com.depromeet.streetdrop.domains.common.annotation.IsLongitude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
@Validated
public class LocationRequestDto {

	@Schema(description = "위도", example = "37.123456")
	@IsLatitude
	@NotNull(message ="Latitude is required")
	private Double latitude;

	@Schema(description = "경도", example = "127.123456")
	@IsLongitude
	@NotNull(message = "Longitude is required")
	private Double longitude;

	@Schema(description = "주소", example = "서울시 강남구 강남동")
	@NotNull(message = "Address is required")
	private String address;
}