package com.depromeet.item;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(indexes = {
		@Index(name = "idx__item_like_item_id", columnList = "item_id"),
		@Index(name = "idx__item_like_user_id", columnList = "user_id")
})
public class ItemLike extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_like_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "item_id", nullable = true, foreignKey = @ForeignKey(NO_CONSTRAINT))
	private Item item;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id", nullable = true, foreignKey = @ForeignKey(NO_CONSTRAINT))
	private User user;

	@Builder
	public ItemLike(Item item, User user) {
		this.item = item;
		this.user = user;
	}

	public boolean isLiked(User user) {
		return this.user.equals(user);
	}

}
