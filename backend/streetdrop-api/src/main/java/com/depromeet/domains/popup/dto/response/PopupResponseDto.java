package com.depromeet.domains.popup.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PopupResponseDto {
    @Schema(description = "데이터", example = "[]")
    List<Object> data;
}
