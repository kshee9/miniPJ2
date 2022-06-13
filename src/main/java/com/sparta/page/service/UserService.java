package com.sparta.page.service;

import com.sparta.page.dto.SignupRequestDto;
import com.sparta.page.model.User;
import com.sparta.page.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    public boolean usercheck(String nickname){

        Optional<User> found = userRepository.findBynickname(nickname);
        if (found.isPresent()){
            return false;
        }
        else {
            return  true;
        }
    }

    //username 중복체크
    @Transactional
    public boolean idcheck(String username){
        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()){
            return false;
        }
        else {
            return  true;
        }
    }

    //회원가입
    @Transactional
    public void registerUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password,nickname);
        userRepository.save(user);

    }
}