package com.depromeet.domains.item.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@NoArgsConstructor
@Getter
public class ItemPointDao {

    private Point point;

    private Long id;

    private String albumThumbnail;

    public ItemPointDao(Point point, Long id, String albumThumbnail) {
        this.point = point;
        this.id = id;
        this.albumThumbnail = albumThumbnail;
    }
}
