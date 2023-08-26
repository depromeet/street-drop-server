package com.streetdrop.domains.item.repository;

import com.streetdrop.domains.item.dao.ItemPointDao;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.streetdrop.external.querydsl.mysql.spatial.MySqlSpatialFunction;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.streetdrop.item.QItem.item;
import static com.streetdrop.item.QItemLocation.itemLocation;
import static com.streetdrop.music.album.QAlbumCover.albumCover;

@Repository
@RequiredArgsConstructor
public class ItemLocationRepository {

    private final JPAQueryFactory queryFactory;

    public List<ItemPointDao> findNearItemsPointsByDistance(Point point, Double distance, Double innerDistance, List<Long> blockedUserIds) {
        return queryFactory.select(
                        Projections.fields(
                                ItemPointDao.class,
                                itemLocation.point,
                                item.id,
                                albumCover.albumThumbnail,
                                MySqlSpatialFunction.mySqlDistanceSphereFunction(itemLocation.point, point)
                                        .loe(String.valueOf(innerDistance))
                                        .as("isInnerDistance")
                        ))
                .from(itemLocation)
                .join(itemLocation.item, item)
                .on(itemLocation.item.id.eq(item.id))
                .join(itemLocation.item.albumCover, albumCover)
                .on(item.albumCover.id.eq(albumCover.id))
                .where(MySqlSpatialFunction.mySqlDistanceSphereFunction(itemLocation.point, point).loe(String.valueOf(distance)))
                .where(itemLocation.item.user.id.notIn(blockedUserIds))
                .fetch();
    }
}
