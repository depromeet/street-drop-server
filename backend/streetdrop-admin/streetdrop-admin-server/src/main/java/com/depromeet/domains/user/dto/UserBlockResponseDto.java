package com.depromeet.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class UserBlockResponseDto {
    private Long id;
    private Long userId;
    private String userNickname;
    private Long blockUserId;
    private String blockUserNickname;
    private LocalDateTime createdAt;
}
