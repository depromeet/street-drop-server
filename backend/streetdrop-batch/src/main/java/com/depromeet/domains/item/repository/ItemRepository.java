package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dto.UserItemCount;
import com.depromeet.item.Item;
import com.depromeet.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("SELECT new com.depromeet.domains.item.dto.UserItemCount(i.user.id, CAST(COUNT(i.id) AS long)) FROM Item i WHERE i.user.id IN :userIds GROUP BY i.user.id")
	List<UserItemCount> countItemsByUserIdIn(@Param("userIds") List<Long> userIds);

	@Query("SELECT i.user FROM Item AS i WHERE i.createdAt > :createdAt")
	List<User> findAllItemUpdateUser(@Param("createdAt") LocalDateTime createdAt);

}
