package com.streetdrop.domains.geo.service;

import com.streetdrop.area.village.VillageArea;
import com.streetdrop.domains.geo.dto.request.ReverseGeocodeRequestDto;
import com.streetdrop.domains.geo.dto.response.ReverseGeocodeResponseDto;
import com.streetdrop.domains.village.service.VillageAreaService;
import com.streetdrop.util.GeomUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeoService {

    private final VillageAreaService villageAreaService;

    public ReverseGeocodeResponseDto reverseGeocode(ReverseGeocodeRequestDto reverseGeocodeRequestDto) {
        Point point = GeomUtil.createPoint(reverseGeocodeRequestDto.getLongitude(), reverseGeocodeRequestDto.getLatitude());
        VillageArea villageArea = villageAreaService.getVillageByLocationPoint(point);
        return new ReverseGeocodeResponseDto(villageArea.getVillageName());
    }

}
