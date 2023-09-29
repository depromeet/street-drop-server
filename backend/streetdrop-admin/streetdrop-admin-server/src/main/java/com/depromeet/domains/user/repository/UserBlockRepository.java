package com.depromeet.domains.user.repository;

import com.depromeet.user.BlockUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlockRepository extends JpaRepository<BlockUser, Long> {

}
