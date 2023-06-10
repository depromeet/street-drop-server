package com.depromeet.domains.area.city.entity;

import com.depromeet.domains.area.state.entity.StateArea;
import com.depromeet.domains.area.village.entity.VillageArea;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
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
    @JoinColumn(name = "state_id")
    private StateArea stateArea;

    @OneToMany(mappedBy = "cityArea")
     private List<VillageArea> villageAreas;

}
