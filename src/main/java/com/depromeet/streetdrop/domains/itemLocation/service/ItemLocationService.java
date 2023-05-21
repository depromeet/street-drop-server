package com.depromeet.streetdrop.domains.itemLocation.service;

import com.depromeet.streetdrop.domains.item.dto.request.ItemRequestDto;
import com.depromeet.streetdrop.domains.itemLocation.dto.request.LocationDto;
import com.depromeet.streetdrop.domains.itemLocation.entity.ItemLocation;
import com.depromeet.streetdrop.global.common.util.GeomUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemLocationService {
	public ItemLocation create(ItemRequestDto requestDto) {
		LocationDto locationDto = requestDto.getLocation();
		Double lat = locationDto.getLatitude();
		Double lon = locationDto.getLongitude();
		Point point = GeomUtil.createPoint(lat, lon);

		return ItemLocation.builder()
				.name(locationDto.getAddress())
				.point(point)
				.build();
	}
}
