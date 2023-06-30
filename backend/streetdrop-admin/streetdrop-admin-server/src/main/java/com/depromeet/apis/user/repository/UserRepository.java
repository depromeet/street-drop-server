package com.depromeet.apis.user.repository;

import com.depromeet.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	Page<User> findAll(Pageable pageable);

	@Query(value = "SELECT DATE_FORMAT(u.createdAt, '%m.%d') AS joinDate, COUNT(u.id) AS joinCount FROM User u GROUP BY joinDate")
	List<Object[]> countUserByCreatedAt();
}
