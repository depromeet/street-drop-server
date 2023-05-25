package com.depromeet.streetdrop.domains.itemLocation.service;

import com.depromeet.streetdrop.domains.area.village.entity.VillageArea;
import com.depromeet.streetdrop.domains.area.village.service.VillageAreaService;
import com.depromeet.streetdrop.domains.item.dto.request.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.itemLocation.dto.request.LocationRequestDto;
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
	private final VillageAreaService villageAreaService;

	public ItemLocation create(ItemRequestDto requestDto) {
		LocationRequestDto locationRequestDto = requestDto.getLocation();
		Double lat = locationRequestDto.getLatitude();
		Double lon = locationRequestDto.getLongitude();
		Point point = gf.createPoint(new Coordinate(lon, lat));
		VillageArea villageArea = villageAreaService.getVillageByLocationPoint(point);

		return ItemLocation.builder()
				.name(locationRequestDto.getAddress())
				.villageArea(villageArea)
				.point(point)
				.build();
	}

	public void updateItemLocation(ItemLocation location, Item item) {
		location.updateItem(item);
	}
}
