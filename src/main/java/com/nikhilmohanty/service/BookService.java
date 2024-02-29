package com.nikhilmohanty.service;

import com.nikhilmohanty.dto.BookCreateDTO;
import com.nikhilmohanty.dto.BookDTO;
import com.nikhilmohanty.dto.BookFindById;
import com.nikhilmohanty.dto.BookUpdateDTO;
import com.nikhilmohanty.entity.Author;
import com.nikhilmohanty.entity.Book;
import com.nikhilmohanty.entity.LibraryBranch;
import com.nikhilmohanty.repository.AuthorRepository;
import com.nikhilmohanty.repository.BookRepository;
import com.nikhilmohanty.repository.LibraryBranchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    LibraryBranchRepository libraryBranchRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooksWithAuthorName(){
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    private BookDTO convertToDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthorName(book.getAuthor().getName());
        return bookDTO;
    }

    public Book createBook(BookCreateDTO bookCreateDTO){
        Book book = new Book();
        book.setTitle(bookCreateDTO.getTitle());

        Author author = authorRepository.findById(bookCreateDTO.getAuthorId()).orElse(null);

        if(author == null){
            throw new IllegalArgumentException("Author with ID" + bookCreateDTO.getAuthorId() + "not found");
        }

        book.setAuthor(author);

        List<LibraryBranch> libraryBranches = new ArrayList<>();
        for (Long branchId : bookCreateDTO.getLibraryBranchId()) {
            LibraryBranch branch = libraryBranchRepository.findById(branchId).orElse(null);
            if (branch != null) {
                libraryBranches.add(branch);
            }
        }
        book.setLibraryBranches(libraryBranches);

        return bookRepository.save(book);

    }

    public Book updateBook(Long bookId, BookUpdateDTO bookUpdateDTO){
        Book book = bookRepository.findById(bookId).orElse(null);

        if(bookUpdateDTO.getTitle()!=null){
            book.setTitle(bookUpdateDTO.getTitle());
        }

        if(bookUpdateDTO.getLibraryBranchesId()!=null){
            book.getLibraryBranches().clear();

            // Fetch library branches from the database based on the provided IDs
            List<Long> branchIds = bookUpdateDTO.getLibraryBranchesId();
            List<LibraryBranch> libraryBranches = libraryBranchRepository.findAllById(branchIds);
            book.setLibraryBranches(libraryBranches);
        }

        return bookRepository.save(book);
    }

    public void deleteBookById(long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));

        // Delete the book
        bookRepository.delete(book);
    }
}
