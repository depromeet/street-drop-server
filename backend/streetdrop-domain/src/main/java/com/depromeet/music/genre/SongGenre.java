package com.depromeet.music.genre;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.music.song.Song;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class SongGenre {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "song_genre_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Builder
    public SongGenre(Song song, Genre genre) {
        this.song = song;
        this.genre = genre;
    }

	public void updateSong(Song song) {
        this.song = song;
	}
}
