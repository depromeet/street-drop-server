package com.depromeet.domains.user.repository;

import com.depromeet.user.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockUserRepository extends JpaRepository<Block, Long> {

	@Query("SELECT b.blockedId FROM Block b WHERE b.blockerId = :blockerId")
	List<Long> findBlockedIdsByBlockerId(Long blockerId);
}
