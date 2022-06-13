package com.sparta.page.model;

import com.sparta.page.dto.NaverBookDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String title;

    private  String description;

    private  String image;

    private  String author;

    private  String publisher;

    private  String isbn;

    public  Books(NaverBookDto naverBookDto){
        this.title = naverBookDto.getTitle();
        this.description = naverBookDto.getDescription();
        this.image = naverBookDto.getImage();
        this.author = naverBookDto.getAuthor();
        this.publisher = naverBookDto.getPublisher();
        this.isbn = naverBookDto.getIsbn();
    }



    public  Books (String title, String description, String image , String author, String publisher , String isbn ) {
        this.title = title;
        this.description =description;
        this.image = image;
        this.author = author;
        this.publisher= publisher;
        this.isbn = isbn;

    }


}
