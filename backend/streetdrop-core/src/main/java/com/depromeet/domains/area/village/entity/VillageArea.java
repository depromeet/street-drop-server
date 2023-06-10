package com.depromeet.domains.area.village.entity;

import com.depromeet.domains.area.city.entity.CityArea;
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

    @Column(nullable = false)
    private String villageName;

    @Column(nullable = false)
    private int villageCode;

    @Column(nullable = false)
    private int version;

    @Column(nullable = false, columnDefinition = "MultiPolygon")
    private MultiPolygon villagePolygon;

    @Column(columnDefinition = "Point")
    private Point villageCenterPoint;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "city_id")
    private CityArea cityArea;

}
