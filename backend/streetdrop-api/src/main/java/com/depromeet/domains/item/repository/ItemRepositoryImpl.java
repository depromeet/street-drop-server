package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dao.ItemDao;
import com.depromeet.domains.user.dto.request.ItemOrderType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.depromeet.area.village.QVillageArea.villageArea;
import static com.depromeet.item.QItem.item;
import static com.depromeet.item.QItemLike.itemLike;
import static com.depromeet.item.QItemLocation.itemLocation;
import static com.depromeet.music.album.QAlbum.album;
import static com.depromeet.music.album.QAlbumCover.albumCover;
import static com.depromeet.music.artist.QArtist.artist;
import static com.depromeet.music.song.QSong.song;
import static com.querydsl.core.types.dsl.Expressions.currentDate;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements QueryDslItemRepository {


    private final JPAQueryFactory queryFactory;

    @Override
    public List<ItemDao> findByUserId(Long userId, long lastCursor, ItemOrderType orderType) {

        DateExpression<Date> currentWeekExpr = currentDate();
        DateTimePath<LocalDateTime> createdAtExpr = item.createdAt;

        var query = queryFactory.select(
                        Projections.constructor(
                                ItemDao.class,
                                orderType == ItemOrderType.MOST_LIKED ?  Expressions.constant(1): createdAtExpr.week().subtract(currentWeekExpr.week()).abs().as("weekAgo"),
                                item.id,
                                item.content,
                                item.createdAt,
                                itemLocation.name,
                                song.name.as("songName"),
                                album.name.as("albumName"),
                                artist.name.as("artistName"),
                                albumCover.albumThumbnail.as("albumThumbnail"),
                                itemLike.count().as("itemCount")
                        )
                ).from(item)
                .join(itemLocation).on(item.id.eq(itemLocation.item.id))
                .join(song).on(item.song.id.eq(song.id))
                .join(album).on(song.album.id.eq(album.id))
                .join(artist).on(album.artist.id.eq(artist.id))
                .join(albumCover).on(item.albumCover.id.eq(albumCover.id))
                .leftJoin(itemLike).on(item.id.eq(itemLike.item.id))
                .where(item.user.id.eq(userId))
                .groupBy(item.id, item.content, item.createdAt, itemLocation.name, song.name, album.name, artist.name, albumCover.albumThumbnail);


        query = switch (orderType) {
            case RECENT -> query.orderBy(item.createdAt.desc());
            case OLDEST -> query.orderBy(item.createdAt.asc());
            case MOST_LIKED -> query.orderBy(itemLike.count().desc());
        };

        return query.fetch();
    }

    @Override
    public List<ItemDao> findByUserIdAndState(Long userId, long lastCursor, ItemOrderType orderType, String state) {

        DateExpression<Date> currentWeekExpr = currentDate();
        DateTimePath<LocalDateTime> createdAtExpr = item.createdAt;

        var query = queryFactory.select(
                        Projections.constructor(
                                ItemDao.class,
                                orderType == ItemOrderType.MOST_LIKED ?  Expressions.constant(1): createdAtExpr.week().subtract(currentWeekExpr.week()).abs().as("weekAgo"),
                                item.id,
                                item.content,
                                item.createdAt,
                                itemLocation.name,
                                song.name.as("songName"),
                                album.name.as("albumName"),
                                artist.name.as("artistName"),
                                albumCover.albumThumbnail.as("albumThumbnail"),
                                itemLike.count().as("itemCount")
                        )
                ).from(item)
                .join(itemLocation).on(item.id.eq(itemLocation.item.id))
                .join(song).on(item.song.id.eq(song.id))
                .join(album).on(song.album.id.eq(album.id))
                .join(artist).on(album.artist.id.eq(artist.id))
                .join(albumCover).on(item.albumCover.id.eq(albumCover.id))
                .join(villageArea).on(itemLocation.villageArea.id.eq(villageArea.id))
                .leftJoin(itemLike).on(item.id.eq(itemLike.item.id))
                .where(item.user.id.eq(userId))
                .where(villageArea.cityArea.stateArea.stateName.eq(state))
                .groupBy(item.id, item.content, item.createdAt, itemLocation.name, song.name, album.name, artist.name, albumCover.albumThumbnail);


        query = switch (orderType) {
            case RECENT -> query.orderBy(item.createdAt.desc());
            case OLDEST -> query.orderBy(item.createdAt.asc());
            case MOST_LIKED -> query.orderBy(itemLike.count().desc());
        };

        return query.fetch();
    }

    @Override
    public List<ItemDao> findByUserIdAndCity(Long userId, long lastCursor, ItemOrderType orderType, String city) {

        DateExpression<Date> currentWeekExpr = currentDate();
        DateTimePath<LocalDateTime> createdAtExpr = item.createdAt;

        var query = queryFactory.select(
                        Projections.constructor(
                                ItemDao.class,
                                orderType == ItemOrderType.MOST_LIKED ?  Expressions.constant(1): createdAtExpr.week().subtract(currentWeekExpr.week()).abs().as("weekAgo"),
                                item.id,
                                item.content,
                                item.createdAt,
                                itemLocation.name,
                                song.name.as("songName"),
                                album.name.as("albumName"),
                                artist.name.as("artistName"),
                                albumCover.albumThumbnail.as("albumThumbnail"),
                                itemLike.count().as("itemCount")
                        )
                ).from(item)
                .join(itemLocation).on(item.id.eq(itemLocation.item.id))
                .join(song).on(item.song.id.eq(song.id))
                .join(album).on(song.album.id.eq(album.id))
                .join(artist).on(album.artist.id.eq(artist.id))
                .join(albumCover).on(item.albumCover.id.eq(albumCover.id))
                .join(villageArea).on(itemLocation.villageArea.id.eq(villageArea.id))
                .leftJoin(itemLike).on(item.id.eq(itemLike.item.id))
                .where(item.user.id.eq(userId))
                .where(villageArea.cityArea.cityName.eq(city))
                .groupBy(item.id, item.content, item.createdAt, itemLocation.name, song.name, album.name, artist.name, albumCover.albumThumbnail);


        query = switch (orderType) {
            case RECENT -> query.orderBy(item.createdAt.desc());
            case OLDEST -> query.orderBy(item.createdAt.asc());
            case MOST_LIKED -> query.orderBy(itemLike.count().desc());
        };

        return query.fetch();
    }
}