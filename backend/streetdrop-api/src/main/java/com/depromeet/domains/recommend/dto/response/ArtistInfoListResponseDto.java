package com.depromeet.domains.recommend.dto.response;

import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicAlbumChartResponseDto;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ArtistInfoListResponseDto {

    private List<ArtistInfoResponseDto> data;

    public static ArtistInfoListResponseDto ofAppleMusicResponseDto(AppleMusicAlbumChartResponseDto appleMusicAlbumChartResponseDto) {
        List<ArtistInfoResponseDto> artistInfoList =
                Optional.ofNullable(appleMusicAlbumChartResponseDto.results.albums)
                        .filter(albums -> !albums.isEmpty())
                        .map(albums -> albums.get(0).data.stream()
                                .map(ArtistInfoResponseDto::fromAppleMusicResponse)
                                .toList()
                        )
                        .orElse(Collections.emptyList());
        return new ArtistInfoListResponseDto(artistInfoList);
    }

}
