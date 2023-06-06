package com.depromeet.streetdrop.domains.user.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.item.entity.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
	@Size(min = 1, max = 20, message = "nickname must be at least 20 characters long")
	private String nickname;

	@Column(length = 100)
	private String idfv;

	@OneToMany(mappedBy = "user")
	private List<Item> items;

	@Builder
	public User(String nickname, String idfv) {
		this.nickname = nickname;
		this.idfv = idfv;
	}
}
