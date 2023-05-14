package com.depromeet.streetdrop.domains.music.song.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.music.album.entity.Album;
import jakarta.persistence.*;
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

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "album_id")
	private Album album;

	@OneToMany(mappedBy = "song")
	private List<Item> items = new ArrayList<>();

}
