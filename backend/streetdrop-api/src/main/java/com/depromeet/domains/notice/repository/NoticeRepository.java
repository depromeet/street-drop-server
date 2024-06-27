package com.depromeet.domains.notice.repository;

import com.depromeet.announcement.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Announcement, Long> {

    boolean existsByIdGreaterThan(Long lastNoticeId);

}
