package com.sparta.page.model;

import com.sparta.page.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Post {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    //유저아이디
    @Column(nullable = false)
    private Long userId;

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.image = postRequestDto.getImage();
        this.author  = postRequestDto.getAuthor();
        this.publisher = postRequestDto.getPublisher();
        this.comment = postRequestDto.getComment();
        this.star = postRequestDto.getStar();
    }

    public Post(Optional<Books> books) {

    }

    public Post(String title, String author, String description, String image, String publisher, int star, String comment) {
        this.title = title;
        this.author = author;
        this.description =description;
        this.image = image;
        this.publisher = publisher;
        this.star = star;
        this.comment = comment;
    }


//    @ManyToMany
//    private List<Folder> folderList;

//    // 관심 상품 생성 시 이용합니다.
//    public Book(BookRequestDto requestDto, Long userId) {
//// 입력값 Validation
//        BookValidator.validateBookInput(requestDto, userId);
//
//// 관심상품을 등록한 회원 Id 저장
//        this.userId = userId;
//        this.title = requestDto.getTitle();
//        this.image = requestDto.getImage();
//
//
//    }

//    public void addFolder(Folder folder) {
//        this.folderList.add(folder);
//    }
}