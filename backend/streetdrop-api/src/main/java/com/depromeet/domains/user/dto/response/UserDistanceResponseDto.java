package com.depromeet.domains.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDistanceResponseDto {
    @Schema(description = "사용자 지정 거리", example = "600")
    private Integer distance;
}
