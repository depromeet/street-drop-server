package com.depromeet.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "default_nick_name")
public class DefaultNickName {

        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long id;

        @Column(length = 5)
        private String preNickName;

        @Column(length = 5)
        private String postNickName;

}