package com.sparta.page.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.page.dto.LoginResponseDto;
import com.sparta.page.security.jwt.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";


    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {


        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());

        // Token 생성
        final String token = JwtTokenUtils.generateJwtToken(userDetails);
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);

        ObjectMapper objectMapper = new ObjectMapper();

        String account = authentication.getName();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setResult(true);
        responseDto.setAccount(account);

        objectMapper.writeValue(response.getWriter(), responseDto);


    }

}
