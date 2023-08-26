package com.streetdrop.repository;

import com.streetdrop.domain.UserDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {

    @Query(value = "{ 'userId' : ?0 }")
    Optional<UserDevice> findByUserId(Long userId);

}
