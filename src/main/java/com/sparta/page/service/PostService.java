package com.sparta.page.service;


import com.sparta.page.dto.PostRequestDto;
import com.sparta.page.model.Post;
import com.sparta.page.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostRequestDto requestDto, Long userId) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Post book = new Post(requestDto, userId);
        postRepository.save(book);

        return book;
    }


    public Page<Post> getAllPost(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return postRepository.findAll(pageable);
    }
}
