package com.sparta.miniteamproject3.controller;


import com.sparta.miniteamproject3.dto.BookRequestDto;
import com.sparta.miniteamproject3.model.Book;
import com.sparta.miniteamproject3.service.BookService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //책객체 생성(커멘트 달려있는)
    @PostMapping("/api/post")
    public Book createBook(@RequestBody BookRequestDto requestDto
                                        @AuthenticationPrincipal User userId) {

        Book book = bookService.createBook(requestDto, userId);

// 응답 보내기
        return book;
    }

}
