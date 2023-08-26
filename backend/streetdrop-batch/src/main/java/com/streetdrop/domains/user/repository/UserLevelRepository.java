package com.streetdrop.domains.user.repository;

import com.streetdrop.user.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLevelRepository extends JpaRepository<UserLevel, Long> {
	UserLevel findUserLevelById(long userLevelId);
}
