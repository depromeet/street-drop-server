package com.depromeet.streetdrop.domains.item.repository;

import com.depromeet.streetdrop.domains.item.entity.ItemLike;
import com.depromeet.streetdrop.domains.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemLikeRepository extends JpaRepository<ItemLike, Long> {
	ItemLike findByItemIdAndUser(Long itemId, User user);
}
