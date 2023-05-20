package com.depromeet.streetdrop.domains.music.artist.service;

import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import com.depromeet.streetdrop.domains.music.artist.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArtistService {
	private final ArtistRepository artistRepository;

	@Transactional(readOnly = true)
	public Artist getOrCreateArtist(String artistName) {
		return artistRepository.findArtistByName(artistName)
				.orElseGet(() -> Artist.builder()
						.name(artistName)
						.build()
		);
	}
}
