package com.sparta.page.service;


import com.sparta.page.dto.PostRequestDto;
import com.sparta.page.model.Post;
import com.sparta.page.repository.BookRepsitory;
import com.sparta.page.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostService {
    private  final PostRepository postRepository;

    private  final BookRepsitory bookRepsitory;


    @Autowired
    public PostService(
            PostRepository postRepository,
            BookRepsitory bookRepsitory

    ) {
        this.postRepository = postRepository;
        this.bookRepsitory = bookRepsitory;
    }


    @Transactional
    public Post createPost(
            PostRequestDto postRequestDto) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기

        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }

    @Transactional
    public Page<Post> getAllPost(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return postRepository.findAll(pageable);
    }
}
