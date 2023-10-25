package com.depromeet.domains.music.genre.service;

import com.depromeet.domains.music.genre.repository.GenreRepository;
import com.depromeet.music.genre.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreService {
	private final GenreRepository genreRepository;

	@Transactional
	public List<Genre> getGenreList(List<String> genreNameList) {
		List<Genre> genreList = genreRepository.findAllByNameIn(genreNameList);
		List<String> existGenreNameList = genreList.stream().map(Genre::getName).toList();
		genreNameList.removeAll(existGenreNameList);
		if (!genreNameList.isEmpty()) {
			List<Genre> newGenreList = genreNameList.stream().map(Genre::new).toList();
			genreRepository.saveAll(newGenreList);
			genreList.addAll(newGenreList);
		}
		return genreList;
	}

}
