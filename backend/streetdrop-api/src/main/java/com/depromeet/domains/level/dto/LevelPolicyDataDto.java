package com.depromeet.domains.level.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LevelPolicyDataDto {
    private final Long level;
    private final String levelName;
    private final String levelDescription;
    private final String levelImage;
}
