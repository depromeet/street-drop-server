package com.depromeet.domains.music.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.music.album.repository.AlbumRepository;
import com.depromeet.domains.music.artist.repository.ArtistRepository;
import com.depromeet.domains.music.dto.request.MusicRequestDto;
import com.depromeet.domains.music.dto.response.MusicResponseDto;
import com.depromeet.domains.music.event.CreateSongGenreEvent;
import com.depromeet.domains.music.song.repository.SongRepository;
import com.depromeet.domains.recommend.dto.response.MusicInfoResponseDto;
import com.depromeet.domains.recommend.dto.response.SearchRecommendCategoryDto;
import com.depromeet.domains.recommend.constant.RecommendType;
import com.depromeet.music.album.Album;
import com.depromeet.music.album.AlbumCover;
import com.depromeet.music.artist.Artist;
import com.depromeet.music.song.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MusicService {
	private final ArtistRepository artistRepository;
	private final SongRepository songRepository;
	private final AlbumRepository albumRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public Song getOrCreateMusic(MusicRequestDto musicRequestDto) {
		String songTitle = musicRequestDto.getTitle();
		String artistName = musicRequestDto.getArtist();
		String albumName = musicRequestDto.getAlbumName();
		String albumImage = musicRequestDto.getAlbumImage();
		List<String> genres = musicRequestDto.getGenre();

		Optional<Artist> artist = findArtist(artistName);
		if (artist.isEmpty()) {
			Artist newArtist = createArtist(artistName);
			Album newAlbum = createAlbum(newArtist, albumName, albumImage);
			Song song = createSong(songTitle, newAlbum);
			eventPublisher.publishEvent(new CreateSongGenreEvent(genres, song));
			return song;
		}

		Optional<Album> album = findAlbum(albumName, albumImage, artist.get());
		if (album.isEmpty()) {
			Album newAlbum = createAlbum(artist.get(), albumName, albumImage);
			Song song = createSong(songTitle, newAlbum);
			eventPublisher.publishEvent(new CreateSongGenreEvent(genres, song));
			return song;
		}

		Optional<Song> song = findSong(songTitle, album.get());
		if (song.isEmpty()) {
			Song newSong = createSong(songTitle, album.get());
			eventPublisher.publishEvent(new CreateSongGenreEvent(genres, newSong));
			return newSong;
		}

		return song.get();
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
		Song newSong = Song.builder()
				.name(songName)
				.album(album)
				.build();

		return songRepository.save(newSong);
	}

	@Transactional(readOnly = true)
	public MusicResponseDto getMusic(Long songId) {
		return songRepository.findSongById(songId)
				.map(MusicResponseDto::new)
				.orElseThrow(() -> new NotFoundException(CommonErrorCode.NOT_FOUND, songId));
	}

	@Transactional(readOnly = true)
	public SearchRecommendCategoryDto getRecentMusic(RecommendType recommendType) {
		var recentSongs = songRepository.findRecentSongs(recommendType.getLimit());
		List<MusicInfoResponseDto> musicInfoResponseDtos = recentSongs.stream()
				.map(MusicInfoResponseDto::ofSong)
				.toList();
		return SearchRecommendCategoryDto.ofMusicInfoResponseDto(recommendType, musicInfoResponseDtos);
	}
}
