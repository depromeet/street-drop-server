package com.depromeet.domains.music.dto.response;

import com.depromeet.domains.item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MusicResponseDto(
        @Schema(description = "음악 제목", example = "Dynamite")
        String title,

        @Schema(description = "음악 아티스트", example = "BTS")
        String artist,

        @Schema(description = "앨범 이미지", example = "https://is2-ssl.mzstatic.com/image/thumb/Music126/v4/03/8d/0e/038d0e52-e96d-f386-b8eb-9f77fa013543/195497146918_Cover.jpg/300x300bb.jpg")
        String albumImage,

        @Schema(description = "음악 장르", example = "K-pop")
        List<String> genre
        ) {
    public MusicResponseDto(Item item) {
        this(
                item.getSong().getName(),
                item.getSong().getAlbum().getArtist().getName(),
                item.getAlbumCover().getAlbumImage(),
                item.getSong().getGenres()
                        .stream()
                        .map(genre -> genre.getName())
                        .toList()
        );
    }
}
