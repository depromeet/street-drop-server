package com.depromeet.streetdrop.domains.area.village.repository;

import com.depromeet.streetdrop.domains.area.village.entity.VillageArea;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VillageAreaRepository extends JpaRepository<VillageArea, Long> {

    @Query(value = "select count(i.id) from ItemLocation i where i.villageArea.villageName = :villageAreaName")
    int countItemsByVillageName(String villageAreaName);

    @Query(value = "select v from VillageArea v where ST_Contains(v.villagePolygon, :point) = true")
    VillageArea findVillageByLocationPoint(Point point);

}