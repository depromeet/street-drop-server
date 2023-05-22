package com.depromeet.streetdrop.domains.itemLocation.service;

import com.depromeet.streetdrop.domains.item.dto.request.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.itemLocation.dto.request.LocationDto;
import com.depromeet.streetdrop.domains.itemLocation.entity.ItemLocation;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemLocationService {
	private final static int WGS84_SRID = 4326;
	private final GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), WGS84_SRID);

	public ItemLocation create(ItemRequestDto requestDto) {
		LocationDto locationDto = requestDto.getLocation();
		Double lat = locationDto.getLatitude();
		Double lon = locationDto.getLongitude();
		Point point = gf.createPoint(new Coordinate(lat, lon));

		return ItemLocation.builder()
				.name(locationDto.getAddress())
				.point(point)
				.build();
	}

	public void updateLocationByItemId(ItemLocation location, Item item) {
		location.updateItem(item);
	}
}
