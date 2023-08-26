package com.streetdrop.domains.music.dto.response;

import com.streetdrop.item.Item;
import com.streetdrop.music.genre.Genre;
import com.streetdrop.music.song.Song;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

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

    public MusicResponseDto(Song song) {
        this(
                song.getName(),
                song.getAlbum().getArtist().getName(),
                song.getAlbum().getAlbumCover().getAlbumImage(),
                song.getGenres()
                        .stream()
                        .map(Genre::getName)
                        .toList()
        );
    }

    @Builder
    public MusicResponseDto(String title, String artist, String albumImage, List<String> genre) {
        this.title = title;
        this.artist = artist;
        this.albumImage = albumImage;
        this.genre = genre;
    }

}
