package com.depromeet.domains.search.controller;


import com.depromeet.domains.search.response.MusicInfoListResponseDto;
import com.depromeet.domains.search.service.MusicSearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/music")
@RequiredArgsConstructor
@Tag(name = "🎵Search", description = "Music Search API")
public class MusicSearchController {

    private final MusicSearchService musicService;

    @GetMapping
    public MusicInfoListResponseDto searchMusic(
            @RequestParam("keyword") @NotBlank String keyword
    ) {
        return musicService.searchMusic(keyword);
    }

}