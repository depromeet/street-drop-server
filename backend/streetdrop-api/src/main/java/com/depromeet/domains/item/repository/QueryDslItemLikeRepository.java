package com.depromeet.domains.item.repository;

import com.depromeet.domains.user.dao.UserItemPointDao;

import java.util.List;

public interface QueryDslItemLikeRepository {
    List<UserItemPointDao> findUserLikedItemsPoints(Long userId);
}
