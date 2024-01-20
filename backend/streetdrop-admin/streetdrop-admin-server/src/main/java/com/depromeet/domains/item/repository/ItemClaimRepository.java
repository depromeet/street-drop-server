package com.depromeet.domains.item.repository;

import com.depromeet.item.ItemClaim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemClaimRepository extends JpaRepository<ItemClaim, Long> {
    Page<ItemClaim> findAll(Pageable pageable);
}
