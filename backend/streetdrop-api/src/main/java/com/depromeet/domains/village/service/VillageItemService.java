package com.depromeet.domains.village.service;

import com.depromeet.area.village.VillageArea;
import com.depromeet.common.error.exception.internal.BusinessException;
import com.depromeet.domains.geo.error.GeoErrorCode;
import com.depromeet.domains.village.dto.request.VillageItemsRequestDto;
import com.depromeet.domains.village.dto.response.VillageItemsCountResponseDto;
import com.depromeet.domains.village.repository.VillageAreaRepository;
import com.depromeet.util.GeomUtil;
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
                () -> new BusinessException(GeoErrorCode.NOT_SUPPORT_LOCATION)
        );
        var response = villageAreaRepository.countItemsByVillageName(villageArea.getVillageName());
        return new VillageItemsCountResponseDto(response, villageArea.getVillageName());
    }

}
