package com.streetdrop.domains.notification.dto;

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
public class NotificationTokenDto {

    @Schema(description = "Fcm 토큰 ", example = "dcin3c9-34fdscsa-3fdscd")
    @NotNull(message = "Token is required")
    private String token;

}
