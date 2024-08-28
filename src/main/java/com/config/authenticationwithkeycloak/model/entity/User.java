package com.config.authenticationwithkeycloak.model.entity;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String gender;
//    private String nationality;
//    private String phoneNumber;
//    private String address;
    private String email;
//    private String profileImage;
}
