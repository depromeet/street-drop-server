package com.depromeet.domains.user.repository;

import com.depromeet.user.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByNickname(String nickname);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<User> findUserByIdfv(String idfv);

	Optional<User> findUserById(Long blockUserID);
}
