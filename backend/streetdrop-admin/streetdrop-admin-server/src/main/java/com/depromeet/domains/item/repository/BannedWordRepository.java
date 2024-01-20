package com.depromeet.domains.item.repository;

import com.depromeet.common.entity.BannedWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannedWordRepository extends JpaRepository<BannedWord, Long> {
    Page<BannedWord> findAll(Pageable pageable);
}
