package com.depromeet.streetdrop.domains.itemLocation.entity;

import com.depromeet.streetdrop.domains.area.village.entity.VillageArea;
import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.item.entity.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

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

	@Column(columnDefinition = "GEOMETRY")
	private Point point;

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "village_id")
	private VillageArea villageArea;

}
