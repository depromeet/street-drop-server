package com.depromeet.domains.item.repository;

import com.depromeet.item.ItemLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemLocationRepository extends JpaRepository<ItemLocation, Long>, QueryDslItemLocationRepository {
}
