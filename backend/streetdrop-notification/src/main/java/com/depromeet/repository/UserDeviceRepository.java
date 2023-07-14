package com.depromeet.repository;

import com.depromeet.domain.UserDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {
    @Query("{'userId': ?0 }")
    void deleteByUserId(Long userId);

}
