package com.depromeet.apis.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSignUpCountRequestDto {
    private LocalDateTime startDate = LocalDateTime.now().minusDays(7);
    private LocalDateTime endDate = LocalDateTime.now();
}
