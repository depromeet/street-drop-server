package com.depromeet.external.applemusic.dto.response;

import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MusicInfoListResponseDto {

    public List<MusicInfoResponseDto> data;

    public static MusicInfoListResponseDto ofAppleMusicResponseDto (
            AppleMusicResponseDto appleMusicResponseDto
    ) {
        MusicInfoListResponseDto musicResponseDto = new MusicInfoListResponseDto();
        musicResponseDto.data = Optional.ofNullable(appleMusicResponseDto.results.songs.get(0))
                .map(songs -> songs.data.stream()
                        .map(MusicInfoResponseDto::fromAppleMusicResponse)
                        .toList()
                )
                .orElse(Collections.emptyList());
        return musicResponseDto;
    }

}
