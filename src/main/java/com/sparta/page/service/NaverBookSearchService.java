package com.sparta.page.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.page.dto.NaverBookDto;
import com.sparta.page.model.Books;
import com.sparta.page.repository.BookRepsitory;
import com.sparta.page.repository.PostRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
public class NaverBookSearchService {

    private final PostRepository postRepository;
    private final BookRepsitory bookRepsitory;


    public NaverBookSearchService(
            PostRepository postRepository,
            BookRepsitory bookRepsitory
    ){
        this.postRepository =postRepository;
        this.bookRepsitory = bookRepsitory;
    }


    public List<NaverBookDto> getNaverBooks(String query) throws IOException {
        // 네이버 쇼핑 API 호출에 필요한 Header, Body 정리
        List<NaverBookDto> naverBookDtoList = getNaverBookDtos(query);

        return naverBookDtoList;
    }

    private List<NaverBookDto> getNaverBookDtos(String query) throws IOException {
        RestTemplate rest = new RestTemplate();//간편하게 REST 방식 API를 호출할 수 있는 spring 내장 클래스
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "zdqMoIkFaK8uKvC2oNY2");
        headers.add("X-Naver-Client-Secret", "LiZfsgtuD5");
        String body = "";
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // 네이버 도서 API 호출 결과 -> naverApiResponseJson (JSON 형태)
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/book.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
        String naverApiResponseJson = responseEntity.getBody();

        // naverApiResponseJson (JSON 형태) -> itemDtoList (자바 객체 형태)
        // - naverApiResponseJson 에서 우리가 사용할 데이터만 추출 -> List<ItemDto> 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode itemsNode = objectMapper.readTree(naverApiResponseJson).get("items");
        List<NaverBookDto> naverBookDtoList = objectMapper
                .readerFor(new TypeReference<List<NaverBookDto>>() {
                })
                .readValue(itemsNode);

        for (NaverBookDto naverBookDto : naverBookDtoList) {
            if (!bookRepsitory.existsByIsbn(naverBookDto.getIsbn())) {
                bookRepsitory.save(new Books(naverBookDto));
            }
        }
        return naverBookDtoList;
    }



    public void  saveBooks( ) throws IOException {

        RestTemplate rest = new RestTemplate();//간편하게 REST 방식 API를 호출할 수 있는 spring 내장 클래스
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "zdqMoIkFaK8uKvC2oNY2");
        headers.add("X-Naver-Client-Secret", "LiZfsgtuD5");
        String body = "";
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        List<String> sort = Arrays.asList("sim","date","count");
        for (int i = 0; i<sort.size(); i++) {

            // 네이버 도서 API 호출 결과 -> naverApiResponseJson (JSON 형태)
            ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/book.json?query=" + "프로그래밍&display=100&sort="+sort.get(i), HttpMethod.GET, requestEntity, String.class);
            String naverApiResponseJson = responseEntity.getBody();

            // naverApiResponseJson (JSON 형태) -> itemDtoList (자바 객체 형태)
            // - naverApiResponseJson 에서 우리가 사용할 데이터만 추출 -> List<ItemDto> 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode itemsNode = objectMapper.readTree(naverApiResponseJson).get("items");
            List<NaverBookDto> naverBookDtoList = objectMapper
                    .readerFor(new TypeReference<List<NaverBookDto>>() {
                    })
                    .readValue(itemsNode);


            for (NaverBookDto naverBookDto : naverBookDtoList) {
                if (!bookRepsitory.existsByIsbn(naverBookDto.getIsbn())) {
                    bookRepsitory.save(new Books(naverBookDto));
                }
            }

        }

    }



}
