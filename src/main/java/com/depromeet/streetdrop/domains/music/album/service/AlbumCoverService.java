package com.depromeet.streetdrop.domains.music.album.service;

import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.album.entity.AlbumCover;
import com.depromeet.streetdrop.domains.music.album.repository.AlbumCoverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlbumCoverService {
	private final AlbumCoverRepository albumCoverRepository;

	public AlbumCover getOrCreateAlbumCover(Album album, String albumImage, String albumCoverName) {
		return albumCoverRepository.findAlbumCoverByAlbum(album)
				.orElseGet(() -> AlbumCover.builder()
						.album(album)
						.albumImage(albumImage)
						.albumThumbnail(albumImage)
						.build()
		);
	}
}
