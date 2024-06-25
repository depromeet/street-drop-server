package com.depromeet.domains.user.repository;

import com.depromeet.user.DefaultNickName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNicknameRepository extends JpaRepository<DefaultNickName, Long> {
}
