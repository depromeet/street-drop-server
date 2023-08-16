package com.depromeet.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSignUpCountRequestDto {
    private LocalDateTime startDate = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.MIN);
    private LocalDateTime endDate = LocalDateTime.now();
}
