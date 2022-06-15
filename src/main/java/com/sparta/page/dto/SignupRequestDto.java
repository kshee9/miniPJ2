package com.sparta.page.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordCheck;
    private String nickname;
}