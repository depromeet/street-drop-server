package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dao.ItemDao;
import com.depromeet.domains.user.dto.request.ItemOrderType;

import java.util.List;

public interface QueryDslItemRepository {
    List<ItemDao> findByUserId(Long userId, long lastCursor, ItemOrderType orderType);
}
