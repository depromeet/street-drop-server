package com.depromeet.item;

import com.depromeet.area.village.VillageArea;
import com.depromeet.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class ItemLocation extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_location_id")
	private Long id;

	@Column(nullable = true)
	private String name;

	@Column(columnDefinition = "Point")
	private Point point;

	@OneToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "village_id")
	private VillageArea villageArea;

	@Builder
	public ItemLocation(String name, Point point, Item item, VillageArea villageArea) {
		this.name = name;
		this.point = point;
		this.item = item;
		this.villageArea = villageArea;
	}

}
