package com.sparta.page.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequestDto {


    private String title;   //검색 결과 문서의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
    private String image;   //썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타납난다.
    private String author;  //저자 정보이다.
    private String publisher;//출판사 정보이다.
    private String description;//검색 결과 문서의 내용을 요약한 패시지 정보이다. 문서 전체의 내용은 link를 따라가면 읽을 수 있다. 패시지에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
    private int star;
    private String comment;//한줄평
    private  String isbn;
}

