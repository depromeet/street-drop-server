package com.depromeet.domains.level.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.level.dto.LevelPolicyResponseDto;
import com.depromeet.domains.level.service.LevelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/level")
@RequiredArgsConstructor
@Tag(name = "Level Controller", description = "Level API")
public class LevelController {

    private final LevelService levelService;

    @Operation(summary = "레벨 정책 확인")
    @GetMapping("/policy")
    public ResponseEntity<LevelPolicyResponseDto> getPolicy() {
        var response = levelService.getPolicy();
        return ResponseDto.ok(response);
    }
}
