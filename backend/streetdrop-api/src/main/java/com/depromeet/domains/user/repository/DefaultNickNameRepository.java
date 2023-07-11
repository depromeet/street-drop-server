package com.depromeet.domains.user.repository;

import com.depromeet.user.DefaultNickName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DefaultNickNameRepository extends JpaRepository<DefaultNickName, Long> {
    @Query(nativeQuery = true,
            value = "SELECT CONCAT(pre.pre_nick_name, ' ', post.post_nick_name) AS fullName" +
                    " FROM (SELECT pre_nick_name FROM default_nick_name ORDER BY RAND() LIMIT 1) AS pre, " +
                    "(SELECT post_nick_name FROM default_nick_name ORDER BY RAND() LIMIT 1) AS post")
    String getDefaultNickNameByRandom();
}
