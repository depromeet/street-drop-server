package com.depromeet.domains.item.repository;

import com.depromeet.item.Item;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i " +
            "JOIN ItemLocation il on il.item.id = i.id " +
            "WHERE ST_Distance_Sphere(il.point, :point) <= :distance")
    List<Item> findNearItemsByDistance(@Param("point") Point point, @Param("distance") Double distance);

    @Query("SELECT i FROM Item i JOIN FETCH i.itemLocation JOIN FETCH i.user JOIN FETCH i.song WHERE i.id = :itemId")
    Optional<Item> findById(Long itemId);

    @Query("""
            SELECT i FROM Item i JOIN FETCH i.itemLocation 
            JOIN FETCH ItemLike JOIN FETCH i.user JOIN FETCH i.song 
            WHERE i.user.id = :userId AND i.id < :lastCursor 
            ORDER BY i.id DESC
            """)
    List<Item> findByUserId(@Param("userId") Long userId, @Param("lastCursor") long lastCursor);

}
