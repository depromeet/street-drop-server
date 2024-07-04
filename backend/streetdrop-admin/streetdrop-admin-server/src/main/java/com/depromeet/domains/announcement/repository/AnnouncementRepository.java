package com.depromeet.domains.announcement.repository;

import com.depromeet.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Notice, Long> {

    Page<Notice> findAll(Pageable pageable);
}
