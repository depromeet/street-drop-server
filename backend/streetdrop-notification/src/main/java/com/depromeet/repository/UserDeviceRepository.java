package com.depromeet.repository;

import com.depromeet.domain.UserDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {

    @Query(value = "{ 'userId' : userId }")
    Optional<UserDevice> findByUserId(@Param("userId") Long userId);

    @Query(value = "{ 'userId' : userId }", fields = "{ 'deviceToken' : 1 }")
    Optional<String> findDeviceTokenByUserId(@Param("userId") Long userId);

    @Query(fields = "{ 'deviceToken' : 1 }")
    List<String> findAllDeviceTokens();

    @Query("{'userId': ?0 }")
    void deleteByUserId(Long userId);

}
