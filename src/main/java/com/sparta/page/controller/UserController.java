package com.sparta.page.controller;

import com.sparta.page.dto.SignupRequestDto;
import com.sparta.page.dto.UserInfoDto;
import com.sparta.page.security.UserDetailsImpl;
import com.sparta.page.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(
            UserService userService
    ) {

        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {

        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {

        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {

        userService.registerUser(requestDto);
    }

    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        return new UserInfoDto(username);
    }

    //닉네임 중복확인
    @GetMapping("/user/nicknameCheck/{nickname}")
    public boolean usernamecheck(@PathVariable String nickname){
        return userService.usercheck(nickname);

    }
    //아이디 중복확인
    @GetMapping("/user/idCheck/{username}")
    public boolean idcheck(@PathVariable String username){
        return userService.idcheck(username);
    }
}