package com.depromeet.domains.village.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.domains.village.repository.VillageAreaRepository;
import com.depromeet.area.village.VillageArea;
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
