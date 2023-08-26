package com.streetdrop.domains.music.service;

import com.streetdrop.common.error.dto.ErrorCode;
import com.streetdrop.common.error.exception.common.NotFoundException;
import com.streetdrop.domains.music.album.repository.AlbumRepository;
import com.streetdrop.domains.music.artist.repository.ArtistRepository;
import com.streetdrop.domains.music.dto.request.MusicRequestDto;
import com.streetdrop.domains.music.dto.response.MusicResponseDto;
import com.streetdrop.domains.music.genre.repository.SongGenreRepository;
import com.streetdrop.domains.music.genre.service.GenreService;
import com.streetdrop.domains.music.song.repository.SongRepository;
import com.streetdrop.music.album.Album;
import com.streetdrop.music.album.AlbumCover;
import com.streetdrop.music.artist.Artist;
import com.streetdrop.music.genre.SongGenre;
import com.streetdrop.music.song.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MusicService {
	private final GenreService genreService;
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
		List<SongGenre> songGenres = genreService.createSongGenres(musicRequestDto.getGenre());

		Optional<Artist> artist = findArtist(artistName);
		if (artist.isEmpty()) {
			Artist newArtist = createArtist(artistName);
			Album newAlbum = createAlbum(newArtist, albumName, albumImage);
			return createSong(songTitle, newAlbum, songGenres);
		}

		Optional<Album> album = findAlbum(albumName, albumImage, artist.get());
		if (album.isEmpty()) {
			Album newAlbum = createAlbum(artist.get(), albumName, albumImage);
			return createSong(songTitle, newAlbum, songGenres);
		}

		Optional<Song> song = findSong(songTitle, album.get());
		return song.orElseGet(() -> createSong(songTitle, album.get(), songGenres));
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
	public Song createSong(String songName, Album album, List<SongGenre> genres) {
		Song newSong = Song.builder()
				.name(songName)
				.album(album)
				.genres(genres)
				.build();

		Song song = songRepository.save(newSong);
		updateSongGenre(genres, song);
		return song;
	}

	private void updateSongGenre(List<SongGenre> songGenres, Song song) {
		for (SongGenre songGenre : songGenres) {
			songGenre.updateSong(song);
		}
	}

	@Transactional(readOnly = true)
	public MusicResponseDto getMusic(Long songId) {
		return songRepository.findSongById(songId)
				.map(MusicResponseDto::new)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, songId));
	}
}
