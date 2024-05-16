package com.depromeet.auth.repository;

import com.depromeet.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, Long> {

    @Query("{ 'username' : ?0 }")
    Optional<Member> findByUsername(String username);

    @Query("{ 'id' : ?0 }")
    Optional<Member> findById(String id);
}
