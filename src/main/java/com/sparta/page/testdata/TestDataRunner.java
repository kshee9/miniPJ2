//package com.sparta.page.testdata;
//
//import com.sparta.page.dto.NaverBookDto;
//import com.sparta.page.model.Books;
//import com.sparta.page.model.Post;
//import com.sparta.page.model.User;
//import com.sparta.page.model.UserRoleEnum;
//import com.sparta.page.repository.BookRepsitory;
//import com.sparta.page.repository.PostRepository;
//import com.sparta.page.repository.UserRepository;
//import com.sparta.page.service.NaverBookSearchService;
//import com.sparta.page.service.PostService;
//import com.sparta.page.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestDataRunner implements ApplicationRunner {
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    PostService postService;
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Autowired
//    BookRepsitory bookRepsitory;
//
//    @Autowired
//    NaverBookSearchService naverBookSearchService;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//// 테스트 User 생성
//        User testUser1 = new User("정국", passwordEncoder.encode("123"), "jg@sparta.com");
//        testUser1 = userRepository.save(testUser1);
//
//
//
//        //테스트 유저의  게시글 작성
//        createTestData(testUser1, "프로그래밍");
//
//
//    }
//
//    private void createTestData(User testUser1, String query) throws IOException {
//        //네이버 검색 api 로 책검색
//        List<NaverBookDto> naverBookDtoList = naverBookSearchService.getNaverBooks(query);
//
//        List<Books> booksList = new ArrayList<>();
//
//        for (NaverBookDto naverBookDto : naverBookDtoList){
//            Books books = new Books();
//
//            books.setId(testUser1.getId());
//
//            books.setTitle(naverBookDto.getTitle());
//            books.setImage(naverBookDto.getImage());
//            books.setAuthor(naverBookDto.getAuthor());
//            books.setDescription(naverBookDto.getDescription());
//            books.setPublisher(naverBookDto.getPublisher());
//            books.setIsbn(naverBookDto.getIsbn());
//
//
//            bookRepsitory.saveAll(booksList);
//        }
//
//    }
//}
