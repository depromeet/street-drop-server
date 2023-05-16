package com.depromeet.streetdrop.domains.area.village.entity;

import com.depromeet.streetdrop.domains.area.city.entity.CityArea;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class VillageArea {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "village_id")
    private Long id;

    @Column(name = "village_name", nullable = false)
    private String villageName;

    @Column(name = "village_code", nullable = false)
    private int villageCode;

    @Column(name = "version", nullable = false)
    private int version;

    @Column(name = "village_polygon", nullable = false, columnDefinition = "MultiPolygon")
    private MultiPolygon villagePolygon;

    @Column(name = "village_center_point", columnDefinition = "Point")
    private Point villageCenterPoint;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "city_id")
    private CityArea cityArea;

}
