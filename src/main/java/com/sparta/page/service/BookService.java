package com.sparta.miniteamproject3.service;


import com.sparta.miniteamproject3.dto.BookRequestDto;
import com.sparta.miniteamproject3.model.Book;
import com.sparta.miniteamproject3.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;

    }

    public Book createBook(BookRequestDto requestDto, Long userId ) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Book book = new Book(requestDto, userId);

        bookRepository.save(book);

        return book;
    }

}
