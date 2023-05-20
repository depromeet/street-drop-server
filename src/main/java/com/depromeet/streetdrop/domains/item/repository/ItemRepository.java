package com.depromeet.streetdrop.domains.item.repository;

import com.depromeet.streetdrop.domains.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
