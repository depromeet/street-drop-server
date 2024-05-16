package com.depromeet.auth.repository;

import com.depromeet.entity.MemberLoginLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberLoginLogRepository extends MongoRepository<MemberLoginLog, Long> {
}
