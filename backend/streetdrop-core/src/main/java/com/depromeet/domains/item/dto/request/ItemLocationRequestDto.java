package com.depromeet.domains.item.dto.request;

import com.depromeet.domains.area.annotation.IsLatitude;
import com.depromeet.domains.area.annotation.IsLongitude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ItemLocationRequestDto {

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
