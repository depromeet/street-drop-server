package com.streetdrop.domains.music.dto;

import com.streetdrop.music.album.Album;
import com.streetdrop.music.album.AlbumCover;
import com.streetdrop.music.artist.Artist;
import com.streetdrop.music.song.Song;

public record MusicDto(
		Artist artist,
		Album album,
		AlbumCover albumCover,
		Song song
) {
}
