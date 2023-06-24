package com.depromeet.domains.item.repository;

import com.depromeet.item.ItemReport;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemReportRepository extends JpaRepository<ItemReport, Long> {
    boolean existsByUserIdAndItemId(Long userId, Long itemId);
}
