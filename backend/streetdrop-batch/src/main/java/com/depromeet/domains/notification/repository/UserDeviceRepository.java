package com.depromeet.domains.notification.repository;

import com.depromeet.notification.UserDevice;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {

    @Query(value = "{ 'userId' : ?0 }")
    Optional<UserDevice> findByUserId(Long userId);

}
