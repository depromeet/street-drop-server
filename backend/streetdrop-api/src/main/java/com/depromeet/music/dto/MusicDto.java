package com.depromeet.music.dto;

import com.depromeet.music.album.Album;
import com.depromeet.music.album.AlbumCover;
import com.depromeet.music.artist.Artist;
import com.depromeet.music.song.Song;

public record MusicDto(
		Artist artist,
		Album album,
		AlbumCover albumCover,
		Song song
) {
}
