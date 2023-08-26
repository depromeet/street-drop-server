package com.streetdrop.area.city;

import com.streetdrop.area.state.StateArea;
import com.streetdrop.area.village.VillageArea;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class CityArea {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(nullable = false)
    private String cityName;

    @Column(nullable = false)
    private int cityCode;

    @Column(nullable = false)
    private int version;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private StateArea stateArea;

    @OneToMany(mappedBy = "cityArea")
    private List<VillageArea> villageAreas;

}
