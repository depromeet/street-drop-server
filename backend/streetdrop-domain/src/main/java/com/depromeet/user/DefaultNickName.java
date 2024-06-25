package com.depromeet.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "default_nick_name")
public class DefaultNickName {

        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long id;

        @Column(length = 5, nullable = false)
        private String preNickName;

        @Column(length = 5, nullable = false)
        private String postNickName;

        public DefaultNickName(String preNickName, String postNickName) {
                this.preNickName = preNickName;
                this.postNickName = postNickName;
        }

}