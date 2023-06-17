package com.depromeet.item.repository;

import com.depromeet.item.ItemLike;
import com.depromeet.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemLikeRepository extends JpaRepository<ItemLike, Long> {
	Optional<ItemLike> findByItemIdAndUser(Long itemId, User user);
	boolean existsByUserIdAndItemId(Long userId, Long itemId);
}
