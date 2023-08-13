package com.depromeet.domains.user.repository;

import com.depromeet.user.User;
import com.depromeet.user.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
