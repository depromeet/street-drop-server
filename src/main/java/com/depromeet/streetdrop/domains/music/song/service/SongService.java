package com.depromeet.streetdrop.domains.music.song.service;

import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import com.depromeet.streetdrop.domains.music.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SongService {
	private final SongRepository songRepository;

	public Song getOrCreateSong(String title, Album album) {
		return songRepository.findSongByName(title)
				.orElseGet(() -> Song.builder()
						.name(title)
						.album(album)
						.build()
		);
	}
}
