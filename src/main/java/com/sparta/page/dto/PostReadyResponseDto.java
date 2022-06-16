package com.sparta.page.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostReadyResponseDto {
    private  String title;

    private  String author;

    private  String description;

    private  String image;

    private  String publisher;

    private  String isbn;

    public PostReadyResponseDto(String title, String author, String description, String image, String publisher,String isbn) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}
