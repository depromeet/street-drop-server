package com.depromeet.streetdrop.domains.item.drop.repository;

import com.depromeet.streetdrop.domains.item.drop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDropRepository extends JpaRepository<Item, Long> {
}
