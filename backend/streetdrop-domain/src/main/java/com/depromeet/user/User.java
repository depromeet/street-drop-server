package com.depromeet.user;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.item.Item;
import com.depromeet.user.vo.MusicApp;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
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

	@Column(length = 20, nullable = false)
	@Setter
	private String nickname;

	@Column(length = 100, nullable = false)
	private String idfv;

	@OneToMany(mappedBy = "user")
	private List<Item> items;

	@Enumerated(STRING)
	@Column(nullable = false)
	private MusicApp musicApp;

	@Builder
	public User(String nickname, String idfv, MusicApp musicApp) {
		this.nickname = nickname;
		this.idfv = idfv;
		this.musicApp = musicApp;
	}

	public MusicApp getMusicApp() {
		return musicApp;
	}

	public User changeNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public void changeMusicApp(MusicApp musicApp) {
		this.musicApp = musicApp;
	}
}
