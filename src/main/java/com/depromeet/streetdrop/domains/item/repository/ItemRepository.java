package com.depromeet.streetdrop.domains.item.repository;

import com.depromeet.streetdrop.domains.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i " +
            "JOIN ItemLocation il on il.item.id = i.id " +
            "WHERE ST_Distance_Sphere(il.point, :point) <= :distance")
    List<Item> findNearItemsByDistance(Point point, Double distance);

}
