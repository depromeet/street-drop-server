package com.depromeet.domains.announcement.repository;

import com.depromeet.announcement.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
