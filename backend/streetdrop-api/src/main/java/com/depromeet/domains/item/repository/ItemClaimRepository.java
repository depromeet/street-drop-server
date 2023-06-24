package com.depromeet.domains.item.repository;

import com.depromeet.item.ItemClaim;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemClaimRepository extends JpaRepository<ItemClaim, Long> {
    boolean existsByUserIdAndItemId(Long userId, Long itemId);
}
