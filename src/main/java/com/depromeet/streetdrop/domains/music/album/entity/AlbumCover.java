package com.depromeet.streetdrop.domains.music.album.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor
@Entity
public class AlbumCover extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "album_cover_id")
	private Long id;

	@OneToOne(mappedBy = "albumCover", cascade = ALL)
	private Album album;

	private String albumImage;

	private String albumThumbnail;

	@Builder
	public AlbumCover(Album album, String albumImage, String albumThumbnail) {
		this.album = album;
		this.albumImage = albumImage;
		this.albumThumbnail = albumThumbnail;
	}
}
