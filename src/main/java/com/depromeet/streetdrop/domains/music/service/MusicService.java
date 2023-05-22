package com.depromeet.streetdrop.domains.music.service;

import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.album.entity.AlbumCover;
import com.depromeet.streetdrop.domains.music.album.repository.AlbumCoverRepository;
import com.depromeet.streetdrop.domains.music.album.repository.AlbumRepository;
import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import com.depromeet.streetdrop.domains.music.artist.repository.ArtistRepository;
import com.depromeet.streetdrop.domains.music.dto.request.MusicDto;
import com.depromeet.streetdrop.domains.music.genre.repository.SongGenreRepository;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import com.depromeet.streetdrop.domains.music.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MusicService {
	private final ArtistRepository artistRepository;
	private final SongRepository songRepository;
	private final AlbumRepository albumRepository;
	private final AlbumCoverRepository albumCoverRepository;
	private final SongGenreRepository songGenreRepository;

	@Transactional(readOnly = true)
	public Song getOrCreateSong(MusicDto musicDto, Album album) {
		String title = musicDto.getTitle();
		return songRepository.findSongByName(title)
				.orElseGet(() -> Song.builder()
						.name(title)
						.album(album)
						.genres(new ArrayList<>())
						.build()
				);
	}

	@Transactional(readOnly = true)
	public Artist getOrCreateArtist(MusicDto musicDto) {
		String artist = musicDto.getArtist();
		return artistRepository.findArtistByName(artist)
				.orElseGet(() -> Artist.builder()
						.name(artist)
						.build()
				);
	}

	@Transactional(readOnly = true)
	public Album getOrCreateAlbum(MusicDto musicDto, Artist artist) {
		return albumRepository.findAlbumByName(musicDto.getAlbumName())
				.orElseGet(() -> Album.builder()
						.name(musicDto.getAlbumName())
						.artist(artist)
						.build());
	}

	@Transactional(readOnly = true)
	public AlbumCover getOrCreateAlbumCover(MusicDto musicDto, Album album) {
		return albumCoverRepository.findAlbumCoverByAlbumImage(musicDto.getAlbumImage())
				.orElseGet(() -> AlbumCover.builder()
						.albumThumbnail(musicDto.getAlbumImage())
						.albumImage(musicDto.getAlbumImage())
						.album(album)
						.build());
	}

	public void updateAlbumByAlbumCover(Album album, AlbumCover albumCover) {
		album.updateAlbumCover(albumCover);
	}
}
