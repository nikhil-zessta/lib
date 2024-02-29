package com.nikhilmohanty.repository;

import com.nikhilmohanty.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book save(Book book);
}
