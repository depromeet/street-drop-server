package com.depromeet.auth.dto.resonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class MemberLoginLogResponseDto {
    private long id;
    private String userId;
    private String name;
    private String loginIp;
    private String userAgent;
    private String loginResult;
    private LocalDateTime loginAt;
}
