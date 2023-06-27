package com.depromeet.domains.item.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class ItemClaimRequestDto {

    @Schema(description = "아이템 아이디", example = "1")
    public Long itemId;

    @Schema(description = "신고 사유", example = "욕설/비방")
    public String reason;

}
