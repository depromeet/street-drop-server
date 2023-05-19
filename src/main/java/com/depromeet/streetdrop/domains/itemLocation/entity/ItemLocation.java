package com.depromeet.streetdrop.domains.itemLocation.entity;

import com.depromeet.streetdrop.domains.area.village.entity.VillageArea;
import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.item.drop.entity.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
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

	@OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "village_id")
	private VillageArea villageArea;

	@Builder
	public ItemLocation(String name, Point point) {
		this.name = name;
		this.point = point;
	}
}
