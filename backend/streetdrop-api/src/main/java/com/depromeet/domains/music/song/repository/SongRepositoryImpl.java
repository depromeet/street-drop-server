package com.depromeet.domains.music.song.repository;

import com.depromeet.domains.recommend.dto.response.MusicInfoResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.depromeet.item.QItem.item;
import static com.depromeet.music.album.QAlbum.album;
import static com.depromeet.music.album.QAlbumCover.albumCover;
import static com.depromeet.music.artist.QArtist.artist;
import static com.depromeet.music.genre.QGenre.genre;
import static com.depromeet.music.genre.QSongGenre.songGenre;
import static com.depromeet.music.song.QSong.song;

@Repository
@RequiredArgsConstructor
public class SongRepositoryImpl implements QueryDslSongRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MusicInfoResponseDto> findRecentSongs(int count) {
        return queryFactory.select(
                        Projections.constructor(
                                MusicInfoResponseDto.class,
                                album.name,
                                artist.name,
                                song.name,
                                albumCover.albumImage,
                                albumCover.albumThumbnail,
                                JPAExpressions
                                        .select(genre.name)
                                        .from(songGenre)
                                        .join(songGenre.genre, genre)
                                        .where(songGenre.song.eq(song))
                                        .groupBy(songGenre.song)
                                        .fetchAll()
                        ))
                .from(item)
                .join(item.song, song).on(item.song.id.eq(song.id))
                .join(song.album, album).on(song.album.id.eq(album.id))
                .join(album.artist, artist).on(album.artist.id.eq(artist.id))
                .join(album.albumCover, albumCover).on(album.albumCover.id.eq(albumCover.id))
                .orderBy(item.createdAt.desc())
                .limit(count)
                .fetch();
    }

}
