package com.nikhilmohanty.controller;

import com.nikhilmohanty.dto.*;
import com.nikhilmohanty.entity.Book;
import com.nikhilmohanty.repository.AuthorRepository;
import com.nikhilmohanty.repository.BookRepository;
import com.nikhilmohanty.repository.LibraryBranchRepository;
import com.nikhilmohanty.service.AuthorService;
import com.nikhilmohanty.service.BookService;
import com.nikhilmohanty.service.LibraryBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class LibController {

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
        return authorService.getAllAuthor();
    }

    @GetMapping("/books")
    public List<BookDTO> getAllBookWithAuthorName(){
        return bookService.getAllBooksWithAuthorName();
    }

    @GetMapping("/branch")
    public List<LibraryBranchDTO> getBooksAndBranch(){
        return libraryBranchService.getBooksAndBranch();
    }


    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBooks(@PathVariable Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return new ResponseEntity<>(book.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Book not found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add/book")
    public Book createBook(@RequestBody BookCreateDTO bookCreateDTO){
        return bookService.createBook(bookCreateDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,@RequestBody BookUpdateDTO bookUpdateDTO){
        Book updateBook = bookService.updateBook(id,bookUpdateDTO);
        return new ResponseEntity<>(updateBook,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
            bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


