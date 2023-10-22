package com.depromeet.auth.repository;

import com.depromeet.auth.entity.MemberLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoginLogRepository extends JpaRepository<MemberLoginLog, Long> {
}
