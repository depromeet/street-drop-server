package com.depromeet.auth.repository;

import com.depromeet.auth.entity.MemberActionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberActionLogRepository extends JpaRepository<MemberActionLog, Long> {
}
