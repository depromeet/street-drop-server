package com.depromeet.apis;


import com.depromeet.music.dto.response.MusicInfoListResponseDto;
import com.depromeet.music.service.MusicService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/music")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping
    public MusicInfoListResponseDto searchMusic(
            @RequestParam("keyword") @NotBlank String keyword
    ) {
        return musicService.searchMusic(keyword);
    }

}