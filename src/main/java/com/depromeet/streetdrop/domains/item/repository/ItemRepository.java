package com.depromeet.streetdrop.domains.item.repository;

import com.depromeet.streetdrop.domains.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(nativeQuery = true,
            value = "select * from item i " +
            "natural join item_location il " +
            "where ST_DISTANCE_SPHERE(" +
            "POINT(ST_Y(ST_GeomFromText(ST_AsText(point))), ST_X(ST_GeomFromText(ST_AsText(point))))," +
            "POINT(:longitude, :latitude)" +
            ") < :distance",
            countQuery = "select count(*) from item i natural join item_location il"
            )
    Page<Item> getNearItemDetail(
            @Param(value = "longitude") Double longitude,
            @Param(value = "latitude") Double latitude,
            @Param(value = "distance") Double distance,
            Pageable pageable
    );

}
