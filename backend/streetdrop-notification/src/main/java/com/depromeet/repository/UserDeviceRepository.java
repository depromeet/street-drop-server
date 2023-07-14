package com.depromeet.repository;

import com.depromeet.domain.UserDevice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {
}
