package com.sparta.page.controller;

import com.sparta.page.dto.PostReadyRequestDto;
import com.sparta.page.dto.PostReadyResponseDto;
import com.sparta.page.dto.PostRequestDto;
import com.sparta.page.dto.PostResponseDto;
import com.sparta.page.model.Post;
import com.sparta.page.repository.PostRepository;
import com.sparta.page.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;


    @Autowired
    public PostController(
            PostService postService,
            PostRepository postRepository

    ) {
        this.postService = postService;
        this.postRepository = postRepository;

    }

    //Post객체 생성(커멘트 달려있는)
    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto postRequestDto) {

       return postService.createPost(postRequestDto);

    }

    // 정보넘겨주는용
    @PostMapping("/api/bookinfo")
    public PostReadyResponseDto getBooksinfo(@RequestBody PostReadyRequestDto requestDto){
        return postService.getBooksInfo(requestDto);
    }
    //삭제
    @DeleteMapping("/api/post/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }
    //게시글 전체 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getAllPost3() {
        return postService.getpost3();
    }

    @GetMapping("/api/post/{postid}")
    public Optional<Post> getOnePost(@PathVariable Long postid){

        return  postRepository.findById(postid);
    }
}
