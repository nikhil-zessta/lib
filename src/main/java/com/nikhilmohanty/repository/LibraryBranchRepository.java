package com.nikhilmohanty.repository;

import com.nikhilmohanty.entity.LibraryBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nikhilmohanty.dto.LibraryBranchDTO;

import java.util.List;

public interface LibraryBranchRepository extends JpaRepository<LibraryBranch, Long> {

        @Query("SELECT new com.nikhilmohanty.dto.LibraryBranchDTO(b.title, lb.name) FROM Book b JOIN b.libraryBranches lb")
        List<LibraryBranchDTO> findBooksAndBranchNames();



}
