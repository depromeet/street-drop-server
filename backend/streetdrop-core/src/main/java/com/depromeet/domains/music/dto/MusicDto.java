package com.depromeet.domains.music.dto;

import com.depromeet.domains.music.album.entity.AlbumCover;
import com.depromeet.domains.music.artist.entity.Artist;
import com.depromeet.domains.music.song.entity.Song;
import com.depromeet.domains.music.album.entity.Album;

public record MusicDto(
		Artist artist,
		Album album,
		AlbumCover albumCover,
		Song song
) {
}
