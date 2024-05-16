package com.depromeet.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.PROTECTED;

@Document(collection = "member")
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
public class Member {

    @Id
    private String id;


    private String username;


    private String email;


    private String name;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Setter
    private String password;

}
