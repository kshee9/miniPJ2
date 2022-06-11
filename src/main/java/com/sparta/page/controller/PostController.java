package com.sparta.page.controller;

import com.sparta.page.dto.PostRequestDto;
import com.sparta.page.model.Post;
import com.sparta.page.repository.PostRepository;
import com.sparta.page.security.UserDetailsImpl;
import com.sparta.page.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;
    @Autowired
    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }

    //Post객체 생성(커멘트 달려있는)
    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Post post = postService.createPost(requestDto, userId);
    // 응답 보내기
        return post;
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
