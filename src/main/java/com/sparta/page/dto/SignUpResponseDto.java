package com.sparta.page.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpResponseDto {
    private  String errorMessage;

    private  boolean result;



    public SignUpResponseDto(boolean b, String errorMessage) {
        this.result =b;
        this.errorMessage = errorMessage;
    }

    public SignUpResponseDto(boolean b) {
        this.result = b;
    }
}
