package com.sparta.page.dto;


import lombok.Getter;

@Getter
public class PostResponseDto {
    private String title;   //검색 결과 문서의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
    private String image;   //썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타납난다.
    private int star;
}
