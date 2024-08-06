package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dao.ItemDao;
import com.depromeet.domains.user.dto.request.ItemOrderType;

import java.util.List;

public interface QueryDslItemRepository {
    List<ItemDao> findByUserId(Long userId, long lastCursor, ItemOrderType orderType);
    List<ItemDao> findByUserIdAndState(Long userId, long lastCursor, ItemOrderType orderType, String state);
    List<ItemDao> findByUserIdAndCity(Long userId, long lastCursor, ItemOrderType orderType, String city);
}
