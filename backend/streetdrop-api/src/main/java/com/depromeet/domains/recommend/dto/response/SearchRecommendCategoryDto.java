package com.depromeet.domains.recommend.dto.response;

import com.depromeet.domains.recommend.constant.RecommendType;
import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicAlbumChartResponseDto;
import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicSongChartResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class SearchRecommendCategoryDto {
    private String title;
    private String type;
    private String description;
    private List<?> content;
    private boolean nextPage;

    public static SearchRecommendCategoryDto ofMusicInfoResponseDto(RecommendType recommendType, List<MusicInfoResponseDto> musicInfoResponseDto) {
        return new SearchRecommendCategoryDto(
                recommendType.getTitle(),
                recommendType.getType(),
                recommendType.getDescription(),
                musicInfoResponseDto,
                recommendType.isNextPage()
        );
    }


    public static SearchRecommendCategoryDto ofAppleMusicResponseDto(RecommendType recommendType, AppleMusicSongChartResponseDto appleMusicSongChartResponseDto) {
        List<MusicInfoResponseDto> musicInfoList = Optional.ofNullable(appleMusicSongChartResponseDto.results.songs)
                .filter(songs -> !songs.isEmpty())
                .map(songs -> songs.get(0).data.stream()
                        .map(MusicInfoResponseDto::fromAppleMusicResponse)
                        .toList()
                )
                .orElse(Collections.emptyList());
        return new SearchRecommendCategoryDto(recommendType.getTitle(), recommendType.getType(), recommendType.getDescription(),
                musicInfoList, recommendType.isNextPage());
    }

    public static SearchRecommendCategoryDto ofAppleMusicResponseDto(RecommendType recommendType, AppleMusicAlbumChartResponseDto appleMusicAlbumChartResponseDto) {
        List<ArtistInfoResponseDto> artistInfoList =
                Optional.ofNullable(appleMusicAlbumChartResponseDto.results.albums)
                        .filter(albums -> !albums.isEmpty())
                        .map(albums -> albums.get(0).data.stream()
                                .map(ArtistInfoResponseDto::fromAppleMusicResponse)
                                .toList()
                        )
                        .orElse(Collections.emptyList());
        return new SearchRecommendCategoryDto(recommendType.getTitle(), recommendType.getType(), recommendType.getDescription(),
                artistInfoList, recommendType.isNextPage());
    }
}
