package com.depromeet.streetdrop.domains.itemLocation.dto.request;

import com.depromeet.streetdrop.domains.common.annotation.IsLatitude;
import com.depromeet.streetdrop.domains.common.annotation.IsLongitude;
import com.depromeet.streetdrop.domains.itemLocation.entity.ItemLocation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocationDto {

	@Schema(description = "위도", example = "37.123456")
	@IsLatitude
	@NotNull(message ="Latitude is required")
	private Double latitude;

	@Schema(description = "경도", example = "127.123456")
	@IsLongitude
	@NotNull(message = "Longitude is required")
	private Double longitude;

	@Schema(description = "주소", example = "서울시 강남구 강남동")
	private String address;

	@Builder
	public LocationDto(Double latitude, Double longitude, String address) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}
}
