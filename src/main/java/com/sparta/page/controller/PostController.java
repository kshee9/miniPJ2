package com.sparta.page.controller;

import com.sparta.page.dto.PostRequestDto;
import com.sparta.page.model.Post;
import com.sparta.page.model.User;
import com.sparta.page.model.UserRoleEnum;
import com.sparta.page.repository.PostRepository;
import com.sparta.page.repository.UserRepository;
import com.sparta.page.security.UserDetailsImpl;
import com.sparta.page.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    private  final UserRepository userRepository;
    @Autowired
    public PostController(
            PostService postService,
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    //Post저장
    @PostMapping("/api/post/{userId}")
    public Post createPost(
            @RequestBody PostRequestDto postRequestDto
    ) {
       return postService.createPost(postRequestDto);
    }

    //삭제
    @DeleteMapping("/api/post/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }
    //게시글 전체 조회
    @GetMapping("/api/posts")
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
}
