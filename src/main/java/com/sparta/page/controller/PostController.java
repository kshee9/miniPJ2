package com.sparta.page.controller;

import com.sparta.page.dto.NaverBookDto;
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

    //Post객체 생성(커멘트 달려있는)  검색한후에 isbn 값을 프론트가 보내주면 검색 메소드를 활용해서 isbn 값으로 다시 검색후 검색한 것의 필요한값들을 넣어서 생성
    @PostMapping("/api/post")
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
