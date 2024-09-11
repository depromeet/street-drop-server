package com.depromeet.domains.recommend.dto.response;

import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicSongChartResponseDto;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class MusicInfoListResponseDto {

    private List<MusicInfoResponseDto> content;

    public static MusicInfoListResponseDto ofAppleMusicResponseDto(AppleMusicSongChartResponseDto appleMusicSongChartResponseDto) {
        List<MusicInfoResponseDto> musicInfoList = Optional.ofNullable(appleMusicSongChartResponseDto.results.songs)
                .filter(songs -> !songs.isEmpty())
                .map(songs -> songs.get(0).data.stream()
                        .map(MusicInfoResponseDto::fromAppleMusicResponse)
                        .toList()
                )
                .orElse(Collections.emptyList());
        return new MusicInfoListResponseDto(musicInfoList);
    }

}
