package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entities.Author;
import com.biblioteca.biblioteca.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping
    public List<Author> authorList(){
        return service.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Optional<Author> authorId(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public Author saveAuthor(@RequestBody Author author){
        return service.createAuthor(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        service.delete(id);
    }


}
