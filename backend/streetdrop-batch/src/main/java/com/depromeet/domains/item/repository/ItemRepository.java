package com.depromeet.domains.item.repository;

import com.depromeet.domains.user.UserItemDto;
import com.depromeet.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("SELECT new com.depromeet.domains.user.UserItemDto(i.user.id, CAST(COUNT(i.id) AS int)) FROM Item i WHERE i.user.id IN :userIds GROUP BY i.user.id")
	List<UserItemDto> countItemsByUserIdIn(@Param("userIds") List<Long> userIds);
}
