package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.BookRequestDTO;
import com.biblioteca.biblioteca.dtos.request.IdRequestDTO;
import com.biblioteca.biblioteca.dtos.response.BookResponseDTO;
import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService service;
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> listAll(){
        List<BookResponseDTO> books = service.listAll();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/id")
    public ResponseEntity<BookResponseDTO> getBy(@RequestBody IdRequestDTO request ){
        Long id = request.getId();
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException(String.format("O livro com o id ['%d'] não encontrado", id)));
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> saveBy(@RequestBody BookRequestDTO bookDTO){
        Book book = service.saveBy(bookDTO);
        BookResponseDTO bookResponseDTO = new BookResponseDTO(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id){
        Optional<BookResponseDTO> book  = service.getById(id);
        if (book.isPresent()){
            service.deleteBy(id);
        } else {
          throw new NotFoundException(String.format("O livro com o id ['%d'] não encontrado", id));
        }

    }

}
