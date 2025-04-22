package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.BookRequestDTO;
import com.biblioteca.biblioteca.dtos.response.BookResponseDTO;
import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService service;
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> bookList(){
        List<BookResponseDTO> books = service.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> bookId(@PathVariable Long id){
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException(String.format("O livro com o id %d não foi encontrado", id)));
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> saveBook(@RequestBody BookRequestDTO bookDTO){
        Book book = service.createBook(bookDTO);
        BookResponseDTO bookResponseDTO = new BookResponseDTO(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        Optional<BookResponseDTO> book  = service.getById(id);
        if (book.isPresent()){
            service.deleteBook(id);
        } else {
          throw new NotFoundException(String.format("O livro de id %d não foi encontrado", id));
        }

    }

}
