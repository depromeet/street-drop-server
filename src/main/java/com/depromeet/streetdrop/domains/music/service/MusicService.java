package com.depromeet.streetdrop.domains.music.service;

import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.album.entity.AlbumCover;
import com.depromeet.streetdrop.domains.music.album.repository.AlbumRepository;
import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import com.depromeet.streetdrop.domains.music.artist.repository.ArtistRepository;
import com.depromeet.streetdrop.domains.music.dto.request.MusicRequestDto;
import com.depromeet.streetdrop.domains.music.genre.repository.SongGenreRepository;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import com.depromeet.streetdrop.domains.music.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MusicService {
	private final ArtistRepository artistRepository;
	private final SongRepository songRepository;
	private final AlbumRepository albumRepository;
	private final SongGenreRepository songGenreRepository;

	@Transactional
	public Song getOrCreateMusic(MusicRequestDto musicRequestDto) {
		String songTitle = musicRequestDto.getTitle();
		String artistName = musicRequestDto.getArtist();
		String albumName = musicRequestDto.getAlbumName();
		String albumImage = musicRequestDto.getAlbumImage();

		Optional<Artist> artist = findArtist(artistName);
		if (artist.isEmpty()) {
			Artist newArtist = createArtist(artistName);
			Album newAlbum = createAlbum(newArtist, albumName, albumImage);
			return createSong(songTitle, newAlbum);
		}

		Optional<Album> album = findAlbum(albumName, albumImage, artist.get());
		if (album.isEmpty()) {
			Album newAlbum = createAlbum(artist.get(), albumName, albumImage);
			return createSong(musicRequestDto.getTitle(), newAlbum);
		}

		Optional<Song> song = findSong(songTitle, album.get());
		return song.orElseGet(() -> createSong(songTitle, album.get()));

	}

	@Transactional(readOnly = true)
	public Optional<Song> findSong(String songTitle, Album album) {
		return songRepository.findSongByNameAndAlbum(songTitle, album);
	}

	@Transactional(readOnly = true)
	public Optional<Artist> findArtist(String artistName) {
		return artistRepository.findArtistByName(artistName);
	}

	@Transactional(readOnly = true)
	public Optional<Album> findAlbum(String albumName, String albumImage, Artist artist) {
		return albumRepository.findAlbumByNameAndImageAndArtist(albumName, albumImage, artist);
	}

	@Transactional
	public Artist createArtist(String artistName) {
		Artist artist = Artist.builder()
				.name(artistName)
				.build();

		return artistRepository.save(artist);
	}

	@Transactional
	public Album createAlbum(Artist artist, String albumName, String albumImage) {
		AlbumCover albumCover = AlbumCover.builder()
				.albumThumbnail(albumImage)
				.albumImage(albumImage)
				.build();

		Album album = Album.builder()
				.name(albumName)
				.albumCover(albumCover)
				.artist(artist)
				.build();

		return albumRepository.save(album);
	}


	@Transactional
	public Song createSong(String songName, Album album) {
		Song song = Song.builder()
				.name(songName)
				.album(album)
				.genres(new ArrayList<>())
				.build();

		return songRepository.save(song);
	}


}
