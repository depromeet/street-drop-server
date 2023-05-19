package com.depromeet.streetdrop.domains.user.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.item.drop.entity.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(length = 20)
	private String nickname;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();

	@Builder
	public User(String nickname, List<Item> items) {
		this.nickname = nickname;
		this.items = items;
	}
}
