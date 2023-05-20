package com.depromeet.streetdrop.domains.user.repository;

import com.depromeet.streetdrop.domains.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByNickname(String nickname);
}
