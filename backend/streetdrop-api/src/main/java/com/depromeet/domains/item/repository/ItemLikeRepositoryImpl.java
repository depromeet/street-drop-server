package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dao.UserItemLikeDao;
import com.depromeet.domains.user.dao.UserItemPointDao;
import com.depromeet.item.QItemLike;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.depromeet.item.QItem.item;
import static com.depromeet.item.QItemLike.itemLike;
import static com.depromeet.item.QItemLocation.itemLocation;
import static com.depromeet.music.album.QAlbum.album;
import static com.depromeet.music.album.QAlbumCover.albumCover;
import static com.depromeet.music.artist.QArtist.artist;
import static com.depromeet.music.song.QSong.song;
import static com.depromeet.user.QUser.user;
import static com.querydsl.core.types.dsl.Expressions.currentDate;
import static com.querydsl.jpa.JPAExpressions.select;

@Repository
@RequiredArgsConstructor
public class ItemLikeRepositoryImpl implements QueryDslItemLikeRepository {

    private final JPAQueryFactory queryFactory;


    public List<UserItemLikeDao> findByUserId(Long userId, Long lastCursor) {

        DateExpression<Date> currentWeekExpr = currentDate();
        DateTimePath<LocalDateTime> createdAtExpr = itemLike.createdAt;
        QItemLike innerItemLike = new QItemLike("innerItemLike");

        return queryFactory.select(
                        Projections.constructor(
                                UserItemLikeDao.class,
                                createdAtExpr.week().subtract(currentWeekExpr.week()).abs().as("weekAgo"),
                                item.id,
                                item.content,
                                itemLike.createdAt,
                                itemLocation.name,
                                song.name.as("songName"),
                                album.name.as("albumName"),
                                artist.name.as("artistName"),
                                albumCover.albumThumbnail.as("albumThumbnail"),
                                select(innerItemLike.id.count()).from(innerItemLike).where(innerItemLike.item.id.eq(item.id)),
                                item.user.id,
                                item.user.nickname
                        )
                ).from(itemLike)
                .join(item).on(item.id.eq(itemLike.item.id))
                .join(itemLocation).on(item.id.eq(itemLocation.item.id))
                .join(song).on(item.song.id.eq(song.id))
                .join(album).on(song.album.id.eq(album.id))
                .join(artist).on(album.artist.id.eq(artist.id))
                .join(albumCover).on(item.albumCover.id.eq(albumCover.id))
                .join(user).on(user.eq(item.user))
                .where(itemLike.user.id.eq(userId))
                .orderBy(itemLike.createdAt.desc())
                .fetch();
    }


    public List<UserItemPointDao> findUserLikedItemsPoints(Long userId) {
        return queryFactory.select(
                        Projections.fields(
                                UserItemPointDao.class,
                                itemLocation.point,
                                item.id,
                                albumCover.albumThumbnail
                        ))
                .from(itemLike)
                .join(itemLike.item, item)
                .on(itemLike.item.id.eq(item.id))
                .join(item.itemLocation, itemLocation)
                .on(item.itemLocation.id.eq(itemLocation.id))
                .join(itemLocation.item.albumCover, albumCover)
                .on(item.albumCover.id.eq(albumCover.id))
                .where(itemLike.user.id.eq(userId))
                .fetch();
    }

}
