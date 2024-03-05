package com.depromeet.item;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.item.vo.ItemClaimStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;
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

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private String itemContent;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public ItemClaim(Long itemId, Long userId, String reason, ItemClaimStatus status, String itemContent) {
        this.itemId = itemId;
        this.userId = userId;
        this.reason = reason;
        this.status = status;
        this.itemContent = itemContent;
    }

}