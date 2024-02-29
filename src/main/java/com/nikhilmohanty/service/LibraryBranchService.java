package com.nikhilmohanty.service;

import com.nikhilmohanty.dto.LibraryBranchDTO;
import com.nikhilmohanty.entity.LibraryBranch;
import com.nikhilmohanty.repository.LibraryBranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryBranchService {

    @Autowired
    private LibraryBranchRepository libraryBranchRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public LibraryBranchService(LibraryBranchRepository libraryBranchRepository) {
        this.libraryBranchRepository = libraryBranchRepository;
    }

    public List<LibraryBranchDTO> getBooksAndBranch(){
        List<LibraryBranchDTO> bookAndBranchNames = libraryBranchRepository.findBooksAndBranchNames();
        return bookAndBranchNames.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LibraryBranchDTO mapToDTO(LibraryBranchDTO result) {
       return result;
    }
}
