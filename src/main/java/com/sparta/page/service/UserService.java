package com.sparta.page.service;

import com.sparta.page.dto.SignUpResponseDto;
import com.sparta.page.dto.SignupRequestDto;
import com.sparta.page.model.User;
import com.sparta.page.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //닉네임 중복체크
    @Transactional
    public boolean usercheck(String nickname) {

        Optional<User> found = userRepository.findBynickname(nickname);
        if (found.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    //username 중복체크
    @Transactional
    public boolean idcheck(String username) {
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    //회원가입 return 값으로 true false값과 에러메세지 출력
    @Transactional
    public SignUpResponseDto registerUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String password2 = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();
        Optional<User> found = userRepository.findByUsername(username);
        Optional<User> found2 = userRepository.findBynickname(nickname);
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();

// 회원가입시 에러메세지들 출력완료
        String errorMessage = "회원가입실패";
        if (password2.length() < 4) {
            errorMessage = "비밀번호 길이가 짧습니다.";
            return new SignUpResponseDto(false,errorMessage);
        }
        else if (password2.contains(username)) {
            errorMessage = "비밀번호에 ID를 포함할 수 없습니다.";
            return new SignUpResponseDto(false,errorMessage);
        }
        else if (!passwordCheck.equals(password2)) {
            errorMessage = "비밀번호가 일치하지 않습니다.";
            return new SignUpResponseDto(false,errorMessage);
        }
        else if (found.isPresent()) {
            errorMessage = "중복된 아이디가 존재합니다";
            return new SignUpResponseDto(false,errorMessage);
        }else if(found2.isPresent()){
            errorMessage = "중복된 닉네임이 존재합니다";
            return new SignUpResponseDto(false,errorMessage);
        }
        signUpResponseDto.setErrorMessage(errorMessage);

        String password = passwordEncoder.encode(requestDto.getPassword());
        User user2 = new User(username, password, nickname);
        userRepository.save(user2);
        return new SignUpResponseDto(true);


    }
}