package com.depromeet.domains.item.repository;

import com.depromeet.item.ItemLike;
import com.depromeet.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemLikeRepository extends JpaRepository<ItemLike, Long> {
    Optional<ItemLike> findByItemIdAndUser(Long itemId, User user);

    boolean existsByUserIdAndItemId(Long userId, Long itemId);

    int countByItemId(Long itemId);

    @Query(value = """
            SELECT il FROM ItemLike il JOIN FETCh Item 
            WHERE il.user.id = :userId AND il.id < :lastCursor 
            ORDER BY il.id DESC
            """)
    List<ItemLike> findByUserId(@Param("userId") Long userId, @Param("lastCursor") long lastCursor);

    Optional<ItemLike> findByItemId(Long itemId);

}
