package com.depromeet.streetdrop.domains.area.state.entity;

import com.depromeet.streetdrop.domains.area.city.entity.CityArea;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class StateArea {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "state_id")
    private Long id;

    @Column(name = "state_name", nullable = false)
    private String stateName;

    @Column(name = "state_code", nullable = false)
    private int stateCode;

    @Column(name = "version", nullable = false)
    private int version;

    @OneToMany(mappedBy = "stateArea", fetch = LAZY)
    private List<CityArea> cityAreas;

}