package com.depromeet.domains.music.song.entity;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.domains.music.album.entity.Album;
import com.depromeet.domains.music.genre.entity.Genre;
import com.depromeet.domains.music.genre.entity.SongGenre;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Song extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "song_id")
	private Long id;

	private String name;

	@ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "album_id")
	private Album album;

	@OneToMany(mappedBy = "song")
	private List<SongGenre> genres = new ArrayList<>();

	@Builder
	public Song(String name, Album album, List<SongGenre> genres) {
		this.name = name;
		this.album = album;
		this.genres = genres;
	}

	public List<Genre> getGenres() {
		return genres.stream()
				.map(SongGenre::getGenre)
				.toList();
	}
}
