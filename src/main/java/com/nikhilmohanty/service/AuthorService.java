package com.nikhilmohanty.service;

import com.nikhilmohanty.dto.AuthorCreateDTO;
import com.nikhilmohanty.dto.AuthorDTO;
import com.nikhilmohanty.entity.Author;
import com.nikhilmohanty.entity.Book;
import com.nikhilmohanty.repository.AuthorRepository;
import com.nikhilmohanty.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthor(){
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(author -> modelMapper.map(author,AuthorDTO.class)).collect(Collectors.toList());
    }

    public Author createAuthor(AuthorCreateDTO authorCreateDTO){
        Author author = new Author();
        author.setName(authorCreateDTO.getName());
        return authorRepository.save(author);
    }
    

}
