package com.depromeet.domains.user.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserItemPointDao {
    private Point point;
    private Long id;
    private String albumThumbnail;
}
