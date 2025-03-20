package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> bookList(){
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> bookId(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return service.createBook(book);
    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        service.deleteBook(id);
    }

}
