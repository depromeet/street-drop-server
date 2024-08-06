package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dao.ItemPointDao;
import com.depromeet.domains.user.dao.UserItemPointDao;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.depromeet.area.village.QVillageArea.villageArea;
import static com.depromeet.external.querydsl.mysql.spatial.MySqlSpatialFunction.mySqlDistanceSphereFunction;
import static com.depromeet.item.QItem.item;
import static com.depromeet.item.QItemLocation.itemLocation;
import static com.depromeet.music.album.QAlbumCover.albumCover;

@Repository
@RequiredArgsConstructor
public class ItemLocationRepositoryImpl implements QueryDslItemLocationRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ItemPointDao> findNearItemsPointsByDistance(Point point, Double distance, Double innerDistance, List<Long> blockedUserIds) {
        return queryFactory.select(
                        Projections.fields(
                                ItemPointDao.class,
                                itemLocation.point,
                                item.id,
                                albumCover.albumThumbnail,
                                mySqlDistanceSphereFunction(itemLocation.point, point)
                                        .loe(String.valueOf(innerDistance))
                                        .as("isInnerDistance")
                        ))
                .from(itemLocation)
                .join(itemLocation.item, item)
                .on(itemLocation.item.id.eq(item.id))
                .join(itemLocation.item.albumCover, albumCover)
                .on(item.albumCover.id.eq(albumCover.id))
                .where(mySqlDistanceSphereFunction(itemLocation.point, point).loe(String.valueOf(distance)))
                .where(itemLocation.item.user.id.notIn(blockedUserIds))
                .fetch();
    }

    @Override
    public List<UserItemPointDao> findUserDropItemsPoints(Long userId) {
        return queryFactory.select(
                        Projections.fields(
                                UserItemPointDao.class,
                                itemLocation.point,
                                item.id,
                                albumCover.albumThumbnail
                        ))
                .from(itemLocation)
                .join(itemLocation.item, item)
                .on(itemLocation.item.id.eq(item.id))
                .join(itemLocation.item.albumCover, albumCover)
                .on(item.albumCover.id.eq(albumCover.id))
                .where(item.user.id.eq(userId))
                .fetch();
    }

    @Override
    public Long countItemsByCity(Long userId, String city) {
        return queryFactory
                .select(itemLocation.count())
                .from(itemLocation)
                .join(item).on(itemLocation.item.id.eq(item.id))
                .join(villageArea).on(itemLocation.villageArea.id.eq(villageArea.id))
                .where(item.user.id.eq(userId))
                .where(villageArea.cityArea.cityName.eq(city))
                .fetchFirst();
    }

    @Override
    public Long countItemsByState(Long userId, String state) {
        return queryFactory
                .select(itemLocation.count())
                .from(itemLocation)
                .join(item).on(itemLocation.item.id.eq(item.id))
                .join(villageArea).on(itemLocation.villageArea.id.eq(villageArea.id))
                .where(item.user.id.eq(userId))
                .where(villageArea.cityArea.stateArea.stateName.eq(state))
                .fetchFirst();
    }

    @Override
    public Long countItems(Long userId) {
        return queryFactory
                .select(itemLocation.count())
                .from(itemLocation)
                .join(item).on(itemLocation.item.id.eq(item.id))
                .where(item.user.id.eq(userId))
                .fetchFirst();
    }

}
