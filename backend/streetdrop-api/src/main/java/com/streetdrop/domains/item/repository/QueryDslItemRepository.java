package com.streetdrop.domains.item.repository;

import com.streetdrop.domains.item.dao.ItemDao;

import java.util.List;

public interface QueryDslItemRepository {
    List<ItemDao> findByUserId(Long userId, long lastCursor);
}
