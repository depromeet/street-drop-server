package com.streetdrop.music.song;

import com.streetdrop.music.album.Album;
import com.streetdrop.music.genre.Genre;
import com.streetdrop.music.genre.SongGenre;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Song {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "song_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn(name = "album_id", nullable = false)
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
