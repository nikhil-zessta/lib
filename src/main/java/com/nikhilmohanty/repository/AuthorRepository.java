package com.nikhilmohanty.repository;

import com.nikhilmohanty.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
