package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dao.UserItemLikeDao;
import com.depromeet.domains.user.dao.UserItemPointDao;
import com.depromeet.domains.user.dto.request.ItemOrderType;

import java.util.List;

public interface QueryDslItemLikeRepository {
    List<UserItemLikeDao> findByUserId(Long userId, Long lastCursor, ItemOrderType itemOrderType);
    List<UserItemPointDao> findUserLikedItemsPoints(Long userId);
    List<UserItemLikeDao> findByUserIdAndState(Long userId, Long lastCursor, ItemOrderType itemOrderType, String state);
    List<UserItemLikeDao> findByUserIdAndCity(Long userId, Long lastCursor, ItemOrderType itemOrderType, String city);
}
