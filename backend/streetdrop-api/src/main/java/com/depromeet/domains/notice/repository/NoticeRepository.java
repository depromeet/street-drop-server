package com.depromeet.domains.notice.repository;

import com.depromeet.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    boolean existsByIdGreaterThan(Long lastNoticeId);

}
