package com.depromeet.streetdrop.domains.music.album.service;

import com.depromeet.streetdrop.domains.item.dto.ItemRequestDto;
import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.album.repository.AlbumRepository;
import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlbumService {
	private final AlbumRepository albumRepository;

	@Transactional(readOnly = true)
	public Album getAlbum(String albumName, Artist artist) {
		return albumRepository.findAlbumByName(albumName).orElse(
				Album.builder()
						.name(albumName)
						.songs(new ArrayList<Song>())
						.artist(artist)
						.build()
		);
	}
}
