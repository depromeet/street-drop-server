package com.streetdrop.domains.item.repository;

import com.streetdrop.item.Item;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, QueryDslItemRepository {

    @Query("SELECT i FROM Item i " +
            "JOIN FETCH i.itemLocation il " +
            "WHERE ST_Distance_Sphere(il.point, :point) <= :distance")
    List<Item> findNearItemsByDistance(@Param("point") Point point, @Param("distance") Double distance);

    @Query("SELECT i FROM Item i JOIN FETCH i.itemLocation JOIN FETCH i.user JOIN FETCH i.song WHERE i.id = :itemId")
    Optional<Item> findById(Long itemId);

}
