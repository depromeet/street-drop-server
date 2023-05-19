package com.depromeet.streetdrop.domains.item.repository;

import com.depromeet.streetdrop.domains.item.dao.ItemPoint;
import com.depromeet.streetdrop.domains.itemLocation.entity.ItemLocation;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemLocationRepository extends JpaRepository<ItemLocation, Long> {
    @Query("SELECT New com.depromeet.streetdrop.domains.item.dao.ItemPoint(il.point, i.id, ac.albumThumbnail) " +
            "FROM ItemLocation il JOIN Item i on il.item.id = i.id " +
            "JOIN AlbumCover ac on il.item.albumCover.id = ac.id " +
            "WHERE ST_Distance_Sphere(il.point, :point) <= :distance")
    List<ItemPoint> findNearItemLocationsByDistance(Point point, double distance);
}
