package com.depromeet.domains.recommend.dto.response;

import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicSongChartResponseDto;
import com.depromeet.music.genre.Genre;
import com.depromeet.music.song.Song;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MusicInfoResponseDto {
    private String albumName;
    private String artistName;
    private String songName;
    private String durationTime;
    private String albumImage;
    private String albumThumbnailImage;
    private List<String> genre;

    public static MusicInfoResponseDto ofSong(Song song) {
        return new MusicInfoResponseDto(
                song.getAlbum().getName(),
                song.getAlbum().getArtist().getName(),
                song.getName(),
                "",
                song.getAlbum().getAlbumCover().getAlbumImage(),
                song.getAlbum().getAlbumCover().getAlbumThumbnail(),
                song.getGenres()
                        .stream()
                        .map(Genre::getName)
                        .toList()
        );
    }

    public static MusicInfoResponseDto fromAppleMusicResponse(AppleMusicSongChartResponseDto.SongData data) {
        final int MINUTES_PER_HOUR = 60;
        final int TO_SEC = 1000;
        final int ALBUM_IMAGE_SIZE = 500;
        final int ALBUM_THUMBNAIL_IMAGE_SIZE = 100;

        BiFunction<String, Integer, String> fillSize = (s, size) ->
                s.replace("{w}", String.valueOf(size)).replace("{h}", String.valueOf(size));

        Function<Integer, String> totalSecondsToTime = totalSeconds -> {
            totalSeconds = totalSeconds / TO_SEC;
            int minutes = totalSeconds / MINUTES_PER_HOUR;
            int seconds = totalSeconds % MINUTES_PER_HOUR;
            return String.format("%d:%02d", minutes, seconds);
        };

        MusicInfoResponseDto musicInfo = new MusicInfoResponseDto();
        musicInfo.albumName = data.attributes.albumName;
        musicInfo.artistName = data.attributes.artistName;
        musicInfo.songName = data.attributes.name;
        musicInfo.durationTime = totalSecondsToTime.apply(data.attributes.durationInMillis);
        musicInfo.albumImage = fillSize.apply(data.attributes.artwork.url, ALBUM_IMAGE_SIZE);
        musicInfo.albumThumbnailImage = fillSize.apply(data.attributes.artwork.url, ALBUM_THUMBNAIL_IMAGE_SIZE);
        musicInfo.genre = data.attributes.genreNames;
        return musicInfo;
    }

}
