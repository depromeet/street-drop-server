package com.depromeet.domains.recommend.dto.response;

import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicAlbumChartResponseDto;
import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicSongChartResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class RecommendCategoryDto {
    private String title;
    private List<?> content;
    private boolean nextPage;

    public static RecommendCategoryDto ofMusicInfoResponseDto(List<MusicInfoResponseDto> musicInfoResponseDto) {
        return new RecommendCategoryDto("많이 드랍된 음악", musicInfoResponseDto, true);
    }


    public static RecommendCategoryDto ofAppleMusicResponseDto(AppleMusicSongChartResponseDto appleMusicSongChartResponseDto) {
        List<MusicInfoResponseDto> musicInfoList = Optional.ofNullable(appleMusicSongChartResponseDto.results.songs)
                .filter(songs -> !songs.isEmpty())
                .map(songs -> songs.get(0).data.stream()
                        .map(MusicInfoResponseDto::fromAppleMusicResponse)
                        .toList()
                )
                .orElse(Collections.emptyList());
        return new RecommendCategoryDto("인기 있는 음악", musicInfoList, true);
    }

    public static RecommendCategoryDto ofAppleMusicResponseDto(AppleMusicAlbumChartResponseDto appleMusicAlbumChartResponseDto) {
        List<ArtistInfoResponseDto> artistInfoList =
                Optional.ofNullable(appleMusicAlbumChartResponseDto.results.albums)
                        .filter(albums -> !albums.isEmpty())
                        .map(albums -> albums.get(0).data.stream()
                                .map(ArtistInfoResponseDto::fromAppleMusicResponse)
                                .toList()
                        )
                        .orElse(Collections.emptyList());
        return new RecommendCategoryDto("아티스트", artistInfoList, false);
    }
}
