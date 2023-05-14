package com.depromeet.streetdrop.domains.user.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
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

	@OneToMany(mappedBy = "user")
	private List<Item> items = new ArrayList<>();

}
