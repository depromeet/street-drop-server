package com.streetdrop.domains.village.repository;

import com.streetdrop.area.village.VillageArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VillageRepository extends JpaRepository<VillageArea, Long> {

    @Query(value = "select v.village_name, count(il.item_location_id) AS itemCount " +
            "from item_location as il " +
            "Join village_area as v on il.village_id = v.village_id " +
            "where il.created_at > '2023-07-13'" +
            "Group by il.village_id order by count(il.item_location_id) desc;"
            , nativeQuery = true)
    List<Object[]> countItemByVillage();

    @Query(value = "select v.village_name, count(il.item_location_id) AS itemCount " +
            "from item_location as il " +
            "Join village_area as v on il.village_id = v.village_id " +
            "where il.created_at > DATE_SUB(NOW(), INTERVAL 3 DAY)" +
            "Group by il.village_id order by count(il.item_location_id) desc;"
            , nativeQuery = true)
    List<Object[]> countItemByVillageIn3Days();

}
