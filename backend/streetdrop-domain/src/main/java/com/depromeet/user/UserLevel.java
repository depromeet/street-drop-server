package com.depromeet.user;

import com.depromeet.user.vo.Level;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users_level")
public class UserLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "level_id")
	private Long id;

	@Column(nullable = false)
	private String levelImage;

	@Column
	@Enumerated(EnumType.STRING)
	private Level level;

	@OneToOne(mappedBy = "userLevel", fetch = FetchType.LAZY)
	private User user;

	@Builder
	public UserLevel(String levelImage, Level level, User user) {
		this.levelImage = levelImage;
		this.level = level;
		this.user = user;
	}
}
