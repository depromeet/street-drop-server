package com.depromeet.music.artist;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.music.album.Album;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Artist extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_id")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	private List<Album> albums;

	@Builder
	public Artist(String name) {
		this.name = name;
	}
}
