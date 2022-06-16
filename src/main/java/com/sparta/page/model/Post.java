package com.sparta.page.model;

import com.sparta.page.dto.PostRequestDto;
import com.sparta.page.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Post {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String author;  //저자 정보이다.

    @Column(nullable = false)
    private String publisher;//출판사 정보이다.

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private int star;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;



    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.image = postRequestDto.getImage();
        this.author = postRequestDto.getAuthor();
        this.publisher = postRequestDto.getPublisher();
        this.comment = postRequestDto.getComment();
        this.star = postRequestDto.getStar();
        this.description = postRequestDto.getDescription();
        this.user = user;
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.image = postRequestDto.getImage();
        this.author = postRequestDto.getAuthor();
        this.publisher = postRequestDto.getPublisher();
        this.comment = postRequestDto.getComment();
        this.star = postRequestDto.getStar();
        this.description = postRequestDto.getDescription();
    }


}