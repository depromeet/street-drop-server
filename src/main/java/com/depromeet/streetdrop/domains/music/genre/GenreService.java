package com.depromeet.streetdrop.domains.music.genre;

import com.depromeet.streetdrop.domains.music.dto.request.MusicRequestDto;
import com.depromeet.streetdrop.domains.music.genre.entity.Genre;
import com.depromeet.streetdrop.domains.music.genre.entity.SongGenre;
import com.depromeet.streetdrop.domains.music.genre.repository.GenreRepository;
import com.depromeet.streetdrop.domains.music.genre.repository.SongGenreRepository;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreService {
	private final GenreRepository genreRepository;
	private final SongGenreRepository songGenreRepository;

	public List<SongGenre> getSongGenres(MusicRequestDto musicRequestDto, Song song) {
		List<String> genres = musicRequestDto.getGenre();
		List<SongGenre> songGenreList = new ArrayList<>();
		for (String genreName : genres) {
			Genre genre = getOrCreateGenre(genreName);
			SongGenre songGenre = getOrCreateSongGenere(song, genre);
			songGenreList.add(songGenre);
		}
		return songGenreList;
	}

	private Genre getOrCreateGenre(String genreName) {
		Genre genre = genreRepository.findGenreByName(genreName)
				.orElseGet(() -> Genre.builder()
						.name(genreName)
						.build()
				);
		genreRepository.save(genre);
		return genre;
	}

	private SongGenre getOrCreateSongGenere(Song song, Genre genre) {
		SongGenre songGenre = songGenreRepository.findSongGenreByGenre(genre)
				.orElseGet(() -> SongGenre.builder()
						.song(song)
						.genre(genre)
						.build()
				);
		songGenreRepository.save(songGenre);
		return songGenre;
	}
}
