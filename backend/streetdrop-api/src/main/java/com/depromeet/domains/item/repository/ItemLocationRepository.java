package com.depromeet.domains.item.repository;

import com.depromeet.item.ItemLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemLocationRepository extends JpaRepository<ItemLocation, Long> {

    @Query("SELECT count(il) FROM ItemLocation il JOIN FETCH il.villageArea va WHERE va.cityArea.cityName = :city")
    Integer countItemsByCity(String city);

    @Query("SELECT count(il) FROM ItemLocation il JOIN FETCH il.villageArea va WHERE va.cityArea.stateArea.stateName = :state")
    Integer countItemsByState(String state);

    @Query("SELECT count(il) FROM ItemLocation il")
    Integer countAll();

}
