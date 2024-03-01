package com.nikhilmohanty.controller;

import com.nikhilmohanty.dto.*;
import com.nikhilmohanty.entity.Author;
import com.nikhilmohanty.entity.Book;
import com.nikhilmohanty.repository.AuthorRepository;
import com.nikhilmohanty.repository.BookRepository;
import com.nikhilmohanty.repository.LibraryBranchRepository;
import com.nikhilmohanty.service.AuthorService;
import com.nikhilmohanty.service.BookService;
import com.nikhilmohanty.service.LibraryBranchService;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class LibController {

    private static final Logger logger = LoggerFactory.getLogger(LibController.class);

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;
    @Autowired
    private LibraryBranchService libraryBranchService;
    @GetMapping("/authors")
    public List<AuthorDTO> getAllAuthor(){
       logger.info(()->"Fetching all authors");
        return authorService.getAllAuthor();
    }

    @PostMapping("/add/author")
    public Author createAuthor(@RequestBody AuthorCreateDTO authorCreateDTO){
        logger.info(()->"Creating author "+ authorCreateDTO);
        return authorService.createAuthor(authorCreateDTO);
    }

    @PostMapping("/delete/author/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        logger.info(()->" Author is deleted with the give id ");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/books")
    public List<BookDTO> getAllBookWithAuthorName(){
        logger.info(()->"Fetching Book Details");
        return bookService.getAllBooksWithAuthorName();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBooks(@PathVariable Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            logger.info(()->"Fetching Books detail with id "+ id);
            return new ResponseEntity<>(book.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Book not found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/branch")
    public List<LibraryBranchDTO> getBooksAndBranch(){
        logger.info(()->"Fetching Branch Details");
        return libraryBranchService.getBooksAndBranch();
    }

    @PostMapping("/add/book")
    public Book createBook(@RequestBody BookCreateDTO bookCreateDTO){
        logger.info(()->"Creating book "+ bookCreateDTO);
        return bookService.createBook(bookCreateDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,@RequestBody BookUpdateDTO bookUpdateDTO){
        Book updateBook = bookService.updateBook(id,bookUpdateDTO);
        logger.info(()->"Updating book of given id "+ id);
        return new ResponseEntity<>(updateBook,HttpStatus.OK);
    }

    @DeleteMapping("/delete/book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
            bookService.deleteBookById(id);
            logger.info(()->"Deleting the book record of given id "+ id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



