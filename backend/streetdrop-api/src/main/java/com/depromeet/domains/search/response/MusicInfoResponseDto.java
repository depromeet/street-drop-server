package com.depromeet.domains.search.response;

import com.depromeet.domains.search.response.apple.Data;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MusicInfoResponseDto {
    public String albumName;
    public String artistName;
    public String songName;
    public String durationTime;
    public String albumImage;
    public String albumThumbnailImage;

    public ArrayList<String> genre;

    public static MusicInfoResponseDto fromAppleMusicResponse(Data data) {
        final int MINUTES_PER_HOUR = 60;
        final int TO_SEC=1000;
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
