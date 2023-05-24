package com.depromeet.streetdrop.domains.music.dto;

import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.album.entity.AlbumCover;
import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import com.depromeet.streetdrop.domains.music.song.entity.Song;

public record MusicDto(
		Artist artist,
		Album album,
		AlbumCover albumCover,
		Song song
) {
}
