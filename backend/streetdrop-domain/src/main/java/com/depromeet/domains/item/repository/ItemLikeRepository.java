package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.entity.ItemLike;
import com.depromeet.domains.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemLikeRepository extends JpaRepository<ItemLike, Long> {
	Optional<ItemLike> findByItemIdAndUser(Long itemId, User user);
	boolean existsByUserIdAndItemId(Long userId, Long itemId);
}
