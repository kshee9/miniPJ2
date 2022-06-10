package com.sparta.miniteamproject3.repository;

import com.sparta.miniteamproject3.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
