package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.AuthorRequestDTO;
import com.biblioteca.biblioteca.dtos.response.AuthorResponseDTO;
import com.biblioteca.biblioteca.entities.Author;
import com.biblioteca.biblioteca.service.AuthorService;
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
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> authorList(){
        List<AuthorResponseDTO> authorDTOS = service.getAllAuthors();
        return ResponseEntity.ok(authorDTOS);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> authorId(@PathVariable Long id){
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> saveAuthor(@RequestBody AuthorRequestDTO authorRequestDTO){
        AuthorResponseDTO authorResponseDTO = service.createAuthor(authorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        service.delete(id);
    }


}
