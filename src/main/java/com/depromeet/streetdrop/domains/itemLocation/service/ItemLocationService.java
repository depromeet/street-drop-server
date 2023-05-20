package com.depromeet.streetdrop.domains.itemLocation.service;

import com.depromeet.streetdrop.domains.item.dto.request.ItemRequestDto;
import com.depromeet.streetdrop.domains.itemLocation.entity.ItemLocation;
import com.depromeet.streetdrop.global.common.util.GeomUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemLocationService {
	public ItemLocation create(ItemRequestDto requestDto) {
		Double lat = requestDto.getLatitude();
		Double lon = requestDto.getLongitude();
		Point point = GeomUtil.createPoint(lat, lon);

		return ItemLocation.builder()
				.name(requestDto.getAddress())
				.point(point)
				.build();
	}
}
