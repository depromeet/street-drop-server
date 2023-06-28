package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dao.ItemPointDao;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.depromeet.external.querydsl.mysql.spatial.MySqlSpatialFunction.mySqlDistanceSphereFunction;
import static com.depromeet.item.QItem.item;
import static com.depromeet.item.QItemLocation.itemLocation;
import static com.depromeet.music.album.QAlbumCover.albumCover;

@Repository
@RequiredArgsConstructor
public class ItemLocationRepository {

    private final JPAQueryFactory queryFactory;

    public List<ItemPointDao> findNearItemsPointsByDistance(Point point, Double distance, Double innerDistance) {
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
                .fetch();
    }
}
