package com.depromeet.music.artist;

import com.depromeet.music.album.Album;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Artist {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "artist_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "artist", cascade = ALL)
	private List<Album> albums;

	@Builder
	public Artist(String name) {
		this.name = name;
	}
}
