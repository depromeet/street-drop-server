package com.depromeet.domains.item.repository;

import com.depromeet.domains.item.dao.ItemPointDao;
import com.depromeet.domains.user.dao.UserItemPointDao;
import org.locationtech.jts.geom.Point;

import java.util.List;

public interface QueryDslItemLocationRepository {
    List<ItemPointDao> findNearItemsPointsByDistance(Point point, Double distance, Double innerDistance, List<Long> blockedUserIds);
    List<UserItemPointDao> findUserDropItemsPoints(Long userId);
    Long countItemsByCity(Long userId, String city);
    Long countItemsByState(Long userId, String state);
    Long countItems(Long userId);
}
