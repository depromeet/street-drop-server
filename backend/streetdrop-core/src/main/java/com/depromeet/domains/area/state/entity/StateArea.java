package com.depromeet.domains.area.state.entity;

import com.depromeet.domains.area.city.entity.CityArea;
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

    @Column(nullable = false)
    private String stateName;

    @Column(nullable = false)
    private int stateCode;

    @Column(nullable = false)
    private int version;

    @OneToMany(mappedBy = "stateArea", fetch = LAZY)
    private List<CityArea> cityAreas;

}