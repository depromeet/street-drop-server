package com.depromeet.domains.search.response;

import com.depromeet.domains.search.response.apple.AppleMusicResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MusicInfoListResponseDto {
    public List<MusicInfoResponseDto> data;

    public static MusicInfoListResponseDto ofAppleMusicResponseDto(
            AppleMusicResponseDto appleMusicResponseDto
    ) {
        MusicInfoListResponseDto musicResponseDto = new MusicInfoListResponseDto();
        musicResponseDto.data = Optional.ofNullable(appleMusicResponseDto.results.songs)
                .map(songs -> songs.data.stream()
                        .map(MusicInfoResponseDto::fromAppleMusicResponse)
                        .toList()
                )
                .orElse(Collections.emptyList());
        return musicResponseDto;
    }
}

