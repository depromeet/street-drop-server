package com.depromeet.streetdrop.domains.music.album.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Album extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "album_id")
	private Long id;

	private String name;

	@OneToOne
	@JoinColumn(name = "album_cover_id")
	private AlbumCover albumCover;

	@OneToMany(mappedBy = "album", fetch = LAZY, cascade = ALL, orphanRemoval = true)
	private List<Song> songs = new ArrayList<>();

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "artist_id")
	private Artist artist;

}
