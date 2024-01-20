package com.depromeet.domains.item.controller;


import com.depromeet.domains.item.service.BannedWordService;
import com.depromeet.domains.user.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banned-words")
public class BannedWordController {
    private final BannedWordService bannedWordService;

    @GetMapping
    public ResponseEntity<?> getBannedWords(
            @PageableDefault(size = 20, page = 0, sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var result = bannedWordService.getBannedWords(pageable);
        return ResponseDto.ok(result);
    }

}
