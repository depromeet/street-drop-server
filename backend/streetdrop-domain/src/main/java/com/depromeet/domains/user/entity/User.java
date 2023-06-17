package com.depromeet.domains.user.entity;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.domains.item.entity.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
