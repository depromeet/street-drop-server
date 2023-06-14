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

    private Boolean isInnerDistance;

    public ItemPointDao(Point point, Long id, String albumThumbnail, boolean isInnerDistance) {
        this.point = point;
        this.id = id;
        this.albumThumbnail = albumThumbnail;
        this.isInnerDistance = isInnerDistance;
    }
}
