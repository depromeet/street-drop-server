package com.depromeet.item;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ItemLike extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_like_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_Id")
	private User user;

	@Builder
	public ItemLike(Item item, User user) {
		this.item = item;
		this.user = user;
	}

	public boolean isLiked(User user) {
		return user.equals(user);
	}

}
