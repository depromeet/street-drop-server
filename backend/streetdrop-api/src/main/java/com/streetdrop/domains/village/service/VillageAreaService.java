package com.streetdrop.domains.village.service;

import com.streetdrop.common.error.dto.ErrorCode;
import com.streetdrop.common.error.exception.common.BusinessException;
import com.streetdrop.domains.village.repository.VillageAreaRepository;
import com.streetdrop.area.village.VillageArea;
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
        return villageAreaRepository.findVillageByLocationPoint(point).orElseThrow(
                () -> new BusinessException(ErrorCode.NOT_SUPPORT_LOCATION)
        );
    }
}
