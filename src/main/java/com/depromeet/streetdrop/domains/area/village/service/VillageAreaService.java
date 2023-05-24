package com.depromeet.streetdrop.domains.area.village.service;

import com.depromeet.streetdrop.domains.area.village.entity.VillageArea;
import com.depromeet.streetdrop.domains.area.village.repository.VillageAreaRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VillageAreaService {

    private final VillageAreaRepository villageAreaRepository;

    public VillageArea getVillageByLocationPoint(Point point) {
        return villageAreaRepository.findVillageByLocationPoint(point);
    }
}
