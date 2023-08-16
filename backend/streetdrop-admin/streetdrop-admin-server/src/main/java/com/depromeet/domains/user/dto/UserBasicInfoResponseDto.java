package com.depromeet.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserBasicInfoResponseDto {
    private Long id;
    private String nickname;
    private String idfv;
    private LocalDateTime createdAt;
}
