package com.depromeet.domains.area.village.service;

import com.depromeet.domains.area.village.repository.VillageAreaRepository;
import com.depromeet.domains.area.village.entity.VillageArea;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VillageAreaService {

    private final VillageAreaRepository villageAreaRepository;

    @Transactional(readOnly = true)
    public VillageArea getVillageByLocationPoint(Point point) {
        return villageAreaRepository.findVillageByLocationPoint(point);
    }
}
