package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.BookRequestDTO;
import com.biblioteca.biblioteca.dtos.response.BookResponseDTO;
import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.entities.Loan;
import com.biblioteca.biblioteca.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<BookResponseDTO> bookList(){
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<BookResponseDTO> bookId(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> saveBook(@RequestBody BookRequestDTO bookDTO){
        Book book = service.createBook(bookDTO);
        BookResponseDTO bookResponseDTO = new BookResponseDTO(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        service.deleteBook(id);
    }

}
