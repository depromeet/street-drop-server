package com.streetdrop.user;

import com.streetdrop.common.entity.BaseTimeEntity;
import com.streetdrop.user.vo.MusicApp;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
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

	@Column(name = "user_level_id", nullable = false)
	private Long userLevelId;

	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name = "user_level_id", insertable = false, updatable = false, nullable = false)
	private UserLevel userLevel;

	@Enumerated(STRING)
	@Column(nullable = false)
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
