package com.depromeet.streetdrop.domains.music.artist.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.music.album.entity.Album;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

	@OneToMany(mappedBy = "artist")
	private List<Album> albums = new ArrayList<>();

}
