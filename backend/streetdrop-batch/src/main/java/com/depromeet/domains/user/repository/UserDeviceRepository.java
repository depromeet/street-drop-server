package com.depromeet.domains.user.repository;

import com.depromeet.user.UserDevice;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceRepository extends JpaRepository<UserDevice, String> {
    Optional<UserDevice> findByUserId(Long userId);
}
