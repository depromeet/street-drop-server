package com.depromeet.user;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.item.Item;
import com.depromeet.user.vo.MusicApp;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@Setter
	private String nickname;

	@Column(length = 100)
	private String idfv;

	@OneToMany(mappedBy = "user")
	private List<Item> items;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "level_id")
	private UserLevel userLevel;

	@Column
	private Long userLevelId;

	@Enumerated(EnumType.STRING)
	private MusicApp musicApp;

	@Builder
	public User(String nickname, String idfv, MusicApp musicApp, Long levelId) {
		this.nickname = nickname;
		this.idfv = idfv;
		this.musicApp = musicApp;
		this.userLevelId = levelId;
	}

	public MusicApp getMusicApp() {
		return musicApp;
	}

	public User changeNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public User changeLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
		return this;
	}

	public void changeMusicApp(MusicApp musicApp) {
		this.musicApp = musicApp;
	}
}
