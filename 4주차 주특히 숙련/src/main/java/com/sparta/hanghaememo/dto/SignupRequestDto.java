package com.sparta.hanghaememo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
//    private String email;
    private boolean admin = false;
    private String adminToken = "";
}