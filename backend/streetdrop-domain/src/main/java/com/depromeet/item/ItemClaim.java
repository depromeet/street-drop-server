package com.depromeet.item;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
public class ItemClaim extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_claim_id")
    private Long id;

    @Column(nullable = false)
    private String reason;

    @Setter
    @Enumerated(EnumType.STRING)
    private ItemClaimStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    @Builder
    public ItemClaim(Item item, User user, ItemClaimStatus status) {
        this.item = item;
        this.user = user;
        this.status = status;
    }

}