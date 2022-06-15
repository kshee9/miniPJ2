package com.sparta.page.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {
    private boolean Result;
    
    private  String errorMessage;

    private  String account;


}
