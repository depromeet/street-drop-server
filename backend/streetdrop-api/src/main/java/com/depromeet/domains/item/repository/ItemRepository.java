package com.depromeet.domains.item.repository;

import com.depromeet.item.Item;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i " +
            "JOIN ItemLocation il on il.item.id = i.id " +
            "WHERE ST_Distance_Sphere(il.point, :point) <= :distance")
    List<Item> findNearItemsByDistance(@Param("point") Point point, @Param("distance") Double distance);

}
