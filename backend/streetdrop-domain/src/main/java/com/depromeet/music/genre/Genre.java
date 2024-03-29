package com.depromeet.music.genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    public Genre(String name) {
        this.name = name;
    }
}
