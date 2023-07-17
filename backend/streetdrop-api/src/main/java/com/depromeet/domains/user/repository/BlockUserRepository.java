package com.depromeet.domains.user.repository;

import com.depromeet.user.BlockUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockUserRepository extends JpaRepository<BlockUser, Long> {

	@Query("SELECT b.blockedId FROM BlockUser b WHERE b.blockerId = :blockerId")
	List<Long> findBlockedIdsByBlockerId(@Param("blockerId") Long blockerId);
}
