package com.depromeet.popup;

import com.depromeet.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Popup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "popup_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String popupName;

    @Column(nullable = false)
    @Setter
    private Boolean isRead;

    public Popup(Long userId, String popupName){
        this.userId = userId;
        this.popupName = popupName;
        this.isRead = false;
    }

}
