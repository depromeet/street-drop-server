package com.depromeet.domains.recommend.dto.response;

import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicAlbumChartResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistInfoResponseDto {

    private static final int MINUTES_PER_HOUR = 60;
    private static final int TO_SEC = 1000;
    private static final int ALBUM_IMAGE_SIZE = 500;
    private static final int ALBUM_THUMBNAIL_IMAGE_SIZE = 100;

    private String artistName;
    private String albumImage;
    private String albumThumbnailImage;

    private static String fillSize(String url, int size) {
        return url.replace("{w}", String.valueOf(size)).replace("{h}", String.valueOf(size));
    }

    private static String convertToTimeFormat(int totalMilliseconds) {
        int totalSeconds = totalMilliseconds / TO_SEC;
        int minutes = totalSeconds / MINUTES_PER_HOUR;
        int seconds = totalSeconds % MINUTES_PER_HOUR;
        return String.format("%d:%02d", minutes, seconds);
    }

    public static ArtistInfoResponseDto fromAppleMusicResponse(AppleMusicAlbumChartResponseDto.AlbumData data) {
        return new ArtistInfoResponseDto(
                data.attributes.artistName,
                fillSize(data.attributes.artwork.url, ALBUM_IMAGE_SIZE),
                fillSize(data.attributes.artwork.url, ALBUM_THUMBNAIL_IMAGE_SIZE)
        );
    }

}
