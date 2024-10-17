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
    private Object content;
    private boolean nextPage;

    public static SearchRecommendCategoryDto ofMusicInfoResponseDto(RecommendType recommendType, List<MusicInfoResponseDto> musicInfoResponseDto) {
        return new SearchRecommendCategoryDto(
                recommendType.getTitle(),
                recommendType.getType(),
                recommendType.getDescription(),
                getContentTypeResponseDto(musicInfoResponseDto),
                recommendType.isNextPage()
        );
    }


    public static SearchRecommendCategoryDto ofAppleMusicResponseDto(RecommendType recommendType, AppleMusicSongChartResponseDto appleMusicSongChartResponseDto) {
        return new SearchRecommendCategoryDto(
                recommendType.getTitle(),
                recommendType.getType(),
                recommendType.getDescription(),
                getContentTypeResponseDto(appleMusicSongChartResponseDto),
                recommendType.isNextPage()
        );
    }

    public static SearchRecommendCategoryDto ofAppleMusicResponseDto(RecommendType recommendType, AppleMusicAlbumChartResponseDto appleMusicAlbumChartResponseDto) {
        return new SearchRecommendCategoryDto(
                recommendType.getTitle(),
                recommendType.getType(),
                recommendType.getDescription(),
                getContentTypeResponseDto(appleMusicAlbumChartResponseDto),
                recommendType.isNextPage()
        );
    }

    private static ContentTypeResponseDto.BasicTypeResponseDto getContentTypeResponseDto(List<MusicInfoResponseDto> musicInfoResponseDto) {
        return new ContentTypeResponseDto.BasicTypeResponseDto(musicInfoResponseDto);
    }

    private static ContentTypeResponseDto.BasicTypeResponseDto getContentTypeResponseDto(AppleMusicSongChartResponseDto appleMusicSongChartResponseDto) {
        List<MusicInfoResponseDto> musicInfoList = Optional.ofNullable(appleMusicSongChartResponseDto.results.songs)
                .filter(songs -> !songs.isEmpty())
                .map(songs -> songs.get(0).data.stream()
                        .map(MusicInfoResponseDto::fromAppleMusicResponse)
                        .toList()
                )
                .orElse(Collections.emptyList());
        return new ContentTypeResponseDto.BasicTypeResponseDto(musicInfoList);
    }


    private static ContentTypeResponseDto.KeywordTypeResponseDto getContentTypeResponseDto(AppleMusicAlbumChartResponseDto appleMusicAlbumChartResponseDto) {
        List<ArtistInfoResponseDto> artistInfoList =
                Optional.ofNullable(appleMusicAlbumChartResponseDto.results.albums)
                        .filter(albums -> !albums.isEmpty())
                        .map(albums -> albums.get(0).data.stream()
                                .map(ArtistInfoResponseDto::fromAppleMusicResponse)
                                .toList()
                        )
                        .orElse(Collections.emptyList());
        return new ContentTypeResponseDto.KeywordTypeResponseDto(artistInfoList);
    }
}
