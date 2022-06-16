package com.sparta.page.controller;

import com.sparta.page.dto.PostReadyRequestDto;
import com.sparta.page.dto.PostReadyResponseDto;
import com.sparta.page.dto.PostRequestDto;
import com.sparta.page.dto.PostResponseDto;
import com.sparta.page.model.Post;
import com.sparta.page.model.User;
import com.sparta.page.repository.PostRepository;
import com.sparta.page.security.UserDetailsImpl;
import com.sparta.page.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public Post createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
       return postService.createPost(postRequestDto,user);
    }

    // 정보넘겨주는용
    @PostMapping("/api/bookinfo")
    public PostReadyResponseDto getBooksinfo(@RequestBody PostReadyRequestDto requestDto){
        return postService.getBooksInfo(requestDto);
    }
    //삭제
    // UserDetails 에서 user 정보를 비교했으나 err로 데이터베이스에서 삭제를 못해
    // username을 통한 비교를 하니 해결 
    @DeleteMapping("/api/post/{postId}")
    public String deletePost(@PathVariable Long postId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
       String user = userDetails.getUsername();
        postService.deleteByidPost(user,postId);
        return "되면좋겠네요";
    }
    //게시글 전체 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getAllPost3() {

        return postService.getpost3();
    }

    @GetMapping("/api/post/{postId}")
    public Optional<Post> getOnePost(@PathVariable Long postId){

        return  postRepository.findById(postId);
    }
    //업데이트
    @PutMapping("/api/post/{postId}")
    public void  updatePost(@RequestBody PostRequestDto postRequestDto , @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user  = userDetails.getUser();
        postService.updatePost(postRequestDto,postId,user);
    }
}
