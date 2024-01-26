package com.depromeet.domains.item.controller;


import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.item.dto.request.BannedWordRequestDto;
import com.depromeet.domains.item.service.BannedWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banned-words")
public class BannedWordController {
    private final BannedWordService bannedWordService;

    @GetMapping
    public ResponseEntity<?> getBannedWords(
            @PageableDefault(
                    size = 20,
                    page = 0,
                    sort = "id",
                    direction = DESC
            ) Pageable pageable
    ) {
        var result = bannedWordService.getBannedWords(pageable);
        return ResponseDto.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createBannedWord(
            @RequestBody BannedWordRequestDto bannedWordRequestDto
    ) {
        var result = bannedWordService.createBannedWord(bannedWordRequestDto.getWord());
        return ResponseDto.ok(result);
    }

    @DeleteMapping("/{bannedWordId}")
    public ResponseEntity<?> deleteBannedWord(
            @PathVariable Long bannedWordId
    ) {
        bannedWordService.deleteBannedWord(bannedWordId);
        return ResponseDto.noContent();
    }

}
