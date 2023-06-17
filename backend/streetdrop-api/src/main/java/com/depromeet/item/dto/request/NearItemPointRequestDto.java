package com.depromeet.item.dto.request;

import com.depromeet.annotation.IsLatitude;
import com.depromeet.annotation.IsLongitude;
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
public class NearItemPointRequestDto {
    private final static Double DEFAULT_DISTANCE = 500.0;

    @Schema(description = "경도", example = "127.123456")
    @IsLongitude
    @NotNull(message= "Longitude is required")
    private Double longitude;

    @Schema(description = "위도", example = "37.123456")
    @IsLatitude
    @NotNull(message= "Latitude is required")
    private Double latitude;

    @Schema(description = "거리 - (단위 - m)", example = "1000")
    private Double distance = DEFAULT_DISTANCE;

    @Schema(description = "반경내의 거리 - (단위 - m)", example = "1000")
    private Double innerDistance = DEFAULT_DISTANCE;

}
