package com.depromeet.repository;

import com.depromeet.domain.UserDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {

    @Query(value = "{ 'userId' : ?0 }")
    Optional<UserDevice> findByUserId(Long userId);

    @Query(value = "{ 'userId' : ?0 }", fields = "{ 'deviceToken' : 1 }")
    Optional<String> findDeviceTokenByUserId(Long userId);

    @Query(value = "{}", fields = "{ 'deviceToken' : 1 }")
    List<String> findAllDeviceTokens();

}
