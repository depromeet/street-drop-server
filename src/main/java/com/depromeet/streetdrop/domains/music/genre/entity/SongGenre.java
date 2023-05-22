package com.depromeet.streetdrop.domains.music.genre.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class SongGenre extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "song_genre_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Builder
    public SongGenre(Song song, Genre genre) {
        this.song = song;
        this.genre = genre;
    }
}
