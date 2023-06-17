package com.depromeet.area.village.repository;

import com.depromeet.area.village.VillageArea;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VillageAreaRepository extends JpaRepository<VillageArea, Long> {

    @Query(value = "select count(i.id) from ItemLocation i where i.villageArea.villageName = :villageAreaName")
    int countItemsByVillageName(@Param("villageAreaName") String villageAreaName);

    @Query(value = "select v from VillageArea v where ST_Contains(v.villagePolygon, :point)")
    Optional<VillageArea> findVillageByLocationPoint(@Param("point") Point point);

}