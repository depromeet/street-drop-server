package com.depromeet.streetdrop.domains.music.genre.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Genre extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    private String name;

	@Builder
	public Genre(String name) {
		this.name = name;
	}
}
