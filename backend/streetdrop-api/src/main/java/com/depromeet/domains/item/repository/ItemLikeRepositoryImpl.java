package com.depromeet.domains.item.repository;

import com.depromeet.domains.user.dao.UserItemPointDao;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.depromeet.item.QItem.item;
import static com.depromeet.item.QItemLike.itemLike;
import static com.depromeet.item.QItemLocation.itemLocation;
import static com.depromeet.music.album.QAlbumCover.albumCover;

@Repository
@RequiredArgsConstructor
public class ItemLikeRepositoryImpl implements QueryDslItemLikeRepository {

    private final JPAQueryFactory queryFactory;

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
