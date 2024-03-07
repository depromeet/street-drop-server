package com.depromeet.domains.level.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LevelPolicyResponseDto {
    private final List<LevelPolicyDataDto> data;
}
