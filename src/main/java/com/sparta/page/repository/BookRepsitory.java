package com.sparta.page.repository;

import com.sparta.page.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepsitory extends JpaRepository<Books, Long> {
    Optional<Books> findById(Long id);
    boolean existsByIsbn(String isbn);


    Books findByisbn(String isbn);
}

