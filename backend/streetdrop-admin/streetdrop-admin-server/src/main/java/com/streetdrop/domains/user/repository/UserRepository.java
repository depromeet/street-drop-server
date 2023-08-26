package com.streetdrop.domains.user.repository;

import com.streetdrop.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT COUNT(u) FROM User u WHERE u.createdAt > '2023-07-13'")
    Long countAllByCreatedAtAfter();

    @Query(value = "SELECT DATE_FORMAT(u.createdAt, '%m.%d') AS joinDate, COUNT(u.id) AS joinCount FROM User u WHERE u.createdAt BETWEEN :startDate AND :endDate GROUP BY joinDate")
    List<Object[]> countUserByCreatedAt(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT COUNT(DISTINCT u.id) AS dropped_users_count FROM User u JOIN Item i ON u.id = i.user.id WHERE u.createdAt > '2023-07-13'")
    int countUserByDropCountIsNotNull();


    @Query(value = "SELECT village_area.village_name, COUNT(item.item_id) AS item_count FROM users " +
            "JOIN item ON users.user_id = item.user_id JOIN item_location ON item_location.item_id = item.item_id " +
            "JOIN village_area ON village_area.village_id = item_location.village_id " +
            "WHERE users.user_id = :userId " +
            "GROUP BY users.user_id, village_area.village_name " +
            "ORDER BY item_count DESC",
            nativeQuery = true
    )
    List<Object[]> countUserDropItemByVillage(@Param("userId") Long userId);

}
