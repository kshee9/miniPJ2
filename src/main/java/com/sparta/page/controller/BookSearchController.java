package com.sparta.page.controller;

import com.sparta.page.dto.NaverBookDto;
import com.sparta.page.service.NaverBookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class BookSearchController {
    private final NaverBookSearchService naverBookSearchService;


    @Autowired
    public BookSearchController(NaverBookSearchService naverBookSearchService) {
        this.naverBookSearchService = naverBookSearchService;
    }
    //책 검색 내용 오픈api가져오기
    @GetMapping("/api/post")
    @ResponseBody
    public List<NaverBookDto> getItems(@RequestParam String query) throws IOException {
        naverBookSearchService.saveBooks();
        return naverBookSearchService.getNaverBooks(query);
    }
// DB에 검색값 저장하려고 돌리는 것이였으나  위에 API 안에 값을 넣어놨기때문에 의미가없다
//    @GetMapping ("/api/savedpost")
//    @ResponseBody
//    public  void  setNaverBookSearchService() throws IOException {
//        naverBookSearchService.saveBooks();
//    }
}
