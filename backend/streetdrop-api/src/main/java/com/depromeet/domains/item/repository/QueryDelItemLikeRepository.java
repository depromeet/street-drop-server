package com.depromeet.domains.item.repository;

import com.depromeet.domains.user.dao.UserItemPointDao;

import java.util.List;

public interface QueryDelItemLikeRepository {
    List<UserItemPointDao> findUserLikedItemsPoints(Long userId);
}
