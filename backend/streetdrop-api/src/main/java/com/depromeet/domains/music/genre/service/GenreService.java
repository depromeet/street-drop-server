package com.depromeet.domains.music.genre.service;

import com.depromeet.music.genre.Genre;
import com.depromeet.music.genre.SongGenre;
import com.depromeet.domains.music.genre.repository.SongGenreRepository;
import com.depromeet.domains.music.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreService {
	private final GenreRepository genreRepository;
	private final SongGenreRepository songGenreRepository;

	public List<SongGenre> createSongGenres(List<String> genres) {
		List<SongGenre> songGenres = genres.stream()
				.map(this::getOrCreateGenre)
				.map(this::createSongGenre)
				.collect(Collectors.toList());

		return songGenres;
	}

	public Genre getOrCreateGenre(String name) {
		Genre genre = genreRepository.findGenreByName(name)
				.orElseGet(() -> Genre.builder()
						.name(name)
						.build()
				);

		return genreRepository.save(genre);
	}

	private SongGenre createSongGenre(Genre genre) {
		SongGenre songGenre = SongGenre.builder()
				.genre(genre)
				.build();

		return songGenreRepository.save(songGenre);
	}
}
