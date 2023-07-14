package com.depromeet.apis.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserActivityResponseDto {
    private String title;
    private String content;
}
