package com.depromeet.music.album;

import com.depromeet.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class AlbumCover {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "album_cover_id")
	private Long id;

	@Column(nullable = false)
	private String albumImage;

	@Column(nullable = false)
	private String albumThumbnail;

	@Builder
	public AlbumCover(String albumImage, String albumThumbnail) {
		this.albumImage = albumImage;
		this.albumThumbnail = albumThumbnail;
	}
}
