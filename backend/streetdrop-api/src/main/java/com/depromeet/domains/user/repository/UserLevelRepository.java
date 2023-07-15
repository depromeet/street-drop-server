package com.depromeet.domains.user.repository;

import com.depromeet.user.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLevelRepository extends JpaRepository<UserLevel, Long> {
	Optional<UserLevel> findLevelByUserId(Long userId);
}
