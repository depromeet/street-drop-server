package com.depromeet.streetdrop.domains.music.service;

import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.album.entity.AlbumCover;
import com.depromeet.streetdrop.domains.music.album.repository.AlbumCoverRepository;
import com.depromeet.streetdrop.domains.music.album.repository.AlbumRepository;
import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import com.depromeet.streetdrop.domains.music.artist.repository.ArtistRepository;
import com.depromeet.streetdrop.domains.music.dto.MusicDto;
import com.depromeet.streetdrop.domains.music.dto.request.MusicRequestDto;
import com.depromeet.streetdrop.domains.music.genre.repository.SongGenreRepository;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import com.depromeet.streetdrop.domains.music.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class MusicService {
	private final ArtistRepository artistRepository;
	private final SongRepository songRepository;
	private final AlbumRepository albumRepository;
	private final AlbumCoverRepository albumCoverRepository;
	private final SongGenreRepository songGenreRepository;

	@Transactional
	public MusicDto getOrCreateMusic(MusicRequestDto musicRequestDto) {
		var artist = getOrCreateArtist(musicRequestDto);
		var album = getOrCreateAlbum(musicRequestDto, artist);
		var albumCover = getOrCreateAlbumCover(musicRequestDto, album);
		var song = getOrCreateSong(musicRequestDto, album);
		return new MusicDto(artist, album, albumCover, song);
	}

	@Transactional(readOnly = true)
	public Song getOrCreateSong(MusicRequestDto musicRequestDto, Album album) {
		String title = musicRequestDto.getTitle();
		return songRepository.findSongByName(title)
				.orElseGet(() -> Song.builder()
						.name(title)
						.album(album)
						.genres(new ArrayList<>())
						.build()
				);
	}

	@Transactional(readOnly = true)
	public Artist getOrCreateArtist(MusicRequestDto musicRequestDto) {
		String artist = musicRequestDto.getArtist();
		return artistRepository.findArtistByName(artist)
				.orElseGet(() -> Artist.builder()
						.name(artist)
						.build()
				);
	}

	@Transactional(readOnly = true)
	public Album getOrCreateAlbum(MusicRequestDto musicRequestDto, Artist artist) {
		return albumRepository.findAlbumByName(musicRequestDto.getAlbumName())
				.orElseGet(() -> Album.builder()
						.name(musicRequestDto.getAlbumName())
						.artist(artist)
						.build());
	}

	@Transactional(readOnly = true)
	public AlbumCover getOrCreateAlbumCover(MusicRequestDto musicRequestDto, Album album) {
		return albumCoverRepository.findAlbumCoverByAlbumImage(musicRequestDto.getAlbumImage())
				.orElseGet(() -> AlbumCover.builder()
						.albumThumbnail(musicRequestDto.getAlbumImage())
						.albumImage(musicRequestDto.getAlbumImage())
						.album(album)
						.build());
	}

	public void updateAlbum(Album album, AlbumCover albumCover) {
		album.updateAlbumCover(albumCover);
	}
}
