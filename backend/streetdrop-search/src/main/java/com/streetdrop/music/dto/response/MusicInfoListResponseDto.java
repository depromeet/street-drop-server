package com.streetdrop.music.dto.response;

import com.streetdrop.music.dto.response.apple.AppleMusicResponseDto;

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
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
        return musicResponseDto;
    }
}

