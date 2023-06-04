package com.depromeet.domains.music.genre.entity;

import com.depromeet.common.entity.BaseTimeEntity;
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
