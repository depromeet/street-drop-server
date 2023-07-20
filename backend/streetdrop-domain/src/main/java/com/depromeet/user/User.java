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

	@Column(name = "user_level_id", nullable = false)
	private Long userLevelId;

	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name = "user_level_id", insertable = false, updatable = false, nullable = false)
	private UserLevel userLevel;

	@Enumerated(EnumType.STRING)
	private MusicApp musicApp;

	@Builder
	public User(String nickname, String idfv, MusicApp musicApp, Long userLevelId) {
		this.nickname = nickname;
		this.idfv = idfv;
		this.musicApp = musicApp;
		this.userLevelId = userLevelId;
	}

	public MusicApp getMusicApp() {
		return musicApp;
	}

	public User changeNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public User changeLevel(Long userLevelId) {
		this.userLevelId = userLevelId;
		return this;
	}

	public void changeMusicApp(MusicApp musicApp) {
		this.musicApp = musicApp;
	}
}
