package com.depromeet.domains.area.village.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.common.util.GeomUtil;
import com.depromeet.domains.area.village.dto.request.VillageItemsRequestDto;
import com.depromeet.domains.area.village.entity.VillageArea;
import com.depromeet.domains.area.village.repository.VillageAreaRepository;
import com.depromeet.domains.area.village.dto.response.VillageItemsCountResponseDto;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VillageItemService {

    private final VillageAreaRepository villageAreaRepository;

    public VillageItemsCountResponseDto countItemsByVillage(String villageName) {
        var response = villageAreaRepository.countItemsByVillageName(villageName);
        return new VillageItemsCountResponseDto(response, villageName);
    }

    public VillageItemsCountResponseDto countItemsInVillageByLocation(VillageItemsRequestDto villageItemsRequestDto) {
        Point point  = GeomUtil.createPoint(villageItemsRequestDto.getLongitude(), villageItemsRequestDto.getLatitude());
        VillageArea villageArea = villageAreaRepository.findVillageByLocationPoint(point).orElseThrow(
                () -> new BusinessException(ErrorCode.NOT_SUPPORT_LOCATION)
        );
        var response = villageAreaRepository.countItemsByVillageName(villageArea.getVillageName());
        return new VillageItemsCountResponseDto(response, villageArea.getVillageName());
    }

}
