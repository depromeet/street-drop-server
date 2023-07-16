package com.depromeet.item;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.item.vo.ItemClaimStatus;
import com.depromeet.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class ItemClaim extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_claim_id")
    private Long id;

    @Column(nullable = false)
    private String reason;

    @Setter
    @Enumerated(STRING)
    @Column(nullable = false)
    private ItemClaimStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    @Builder
    public ItemClaim(Item item, User user, String reason, ItemClaimStatus status) {
        this.item = item;
        this.user = user;
        this.reason = reason;
        this.status = status;
    }

}