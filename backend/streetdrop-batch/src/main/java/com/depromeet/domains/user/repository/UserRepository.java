package com.depromeet.domains.user.repository;

import com.depromeet.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByCreatedAtBefore(LocalDateTime createdAt);
}
