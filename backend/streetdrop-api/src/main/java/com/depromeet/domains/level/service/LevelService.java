package com.depromeet.domains.level.service;

import com.depromeet.domains.level.data.LevelPolicy;
import com.depromeet.domains.level.dto.LevelPolicyDataDto;
import com.depromeet.domains.level.dto.LevelPolicyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class LevelService {

    public LevelPolicyResponseDto getPolicy() {
        return new LevelPolicyResponseDto(
                Arrays.stream(LevelPolicy.values())
                        .map(levelPolicy -> new LevelPolicyDataDto(
                                levelPolicy.getLevel(),
                                levelPolicy.getLevelName(),
                                levelPolicy.getDescription(),
                                levelPolicy.getLevelImage()
                        ))
                        .toList()
        );
    }

}
