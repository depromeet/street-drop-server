package com.depromeet.streetdrop.domains.item.service;

import com.depromeet.streetdrop.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.response.PoiResponseDto;
import com.depromeet.streetdrop.domains.item.repository.ItemLocationRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemLocationRepository itemLocationRepository;
    private final GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

    public PoiResponseDto getNearItemPoints(NearItemRequestDto nearItemRequestDto) {
        Point point = gf.createPoint(new Coordinate(nearItemRequestDto.getLongitude(), nearItemRequestDto.getLatitude()));
        var poiDtoList = itemLocationRepository.findNearItemLocationsByDistance(point, nearItemRequestDto.getDistance())
                .stream().map(PoiResponseDto.PoiDto::fromItemPoint).toList();
        return new PoiResponseDto(poiDtoList);
    }

}
