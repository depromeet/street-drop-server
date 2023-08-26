package com.streetdrop.domains.geo.dto.request;

import com.streetdrop.annotation.IsLatitude;
import com.streetdrop.annotation.IsLongitude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Validated
@ParameterObject
public class ReverseGeocodeRequestDto {
    @Schema(description = "위도", example = "37.123456")
    @IsLatitude
    @NotNull(message = "Latitude is required")
    private Double latitude;

    @Schema(description = "경도", example = "127.123456")
    @IsLongitude
    @NotNull(message = "Longitude is required")
    private Double longitude;
}
