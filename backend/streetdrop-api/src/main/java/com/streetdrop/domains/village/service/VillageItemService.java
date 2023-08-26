package com.streetdrop.domains.village.service;

import com.streetdrop.area.village.VillageArea;
import com.streetdrop.common.error.dto.ErrorCode;
import com.streetdrop.common.error.exception.common.BusinessException;
import com.streetdrop.domains.village.dto.request.VillageItemsRequestDto;
import com.streetdrop.domains.village.dto.response.VillageItemsCountResponseDto;
import com.streetdrop.domains.village.repository.VillageAreaRepository;
import com.streetdrop.util.GeomUtil;
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
