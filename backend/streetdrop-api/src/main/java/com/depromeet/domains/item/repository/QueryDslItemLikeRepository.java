package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dao.ItemLocationCountDao;
import com.depromeet.domains.item.dao.UserItemLikeDao;
import com.depromeet.domains.user.dao.UserItemPointDao;
import com.depromeet.domains.user.dto.request.ItemOrderType;

import java.util.List;

public interface QueryDslItemLikeRepository {
    List<UserItemPointDao> findUserLikedItemsPoints(Long userId);
    List<UserItemLikeDao> findByUserId(Long userId, Long lastCursor, ItemOrderType itemOrderType);
    List<UserItemLikeDao> findByUserIdAndState(Long userId, Long lastCursor, ItemOrderType itemOrderType, String state);
    List<UserItemLikeDao> findByUserIdAndCity(Long userId, Long lastCursor, ItemOrderType itemOrderType, String city);
    List<ItemLocationCountDao> countItemsGroupByState(Long userId);
    Long countItemsByState(Long userId, String state);
    Long countItemsByCity(Long userId, String city);
    Long countItems(Long userId);
}
