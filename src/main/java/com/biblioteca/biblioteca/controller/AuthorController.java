package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.AuthorRequestDTO;
import com.biblioteca.biblioteca.dtos.response.AuthorResponseDTO;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    public AuthorController(AuthorService service) {
        this.service = service;
    }

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
                .orElseThrow(() -> new NotFoundException(String.format("Autor com o id %d não encontrado", id)));
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> saveAuthor(@RequestBody AuthorRequestDTO authorRequestDTO){
        AuthorResponseDTO authorResponseDTO = service.createAuthor(authorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        Optional<AuthorResponseDTO> author = service.getById(id);
        if (author.isPresent()){
            service.delete(id);
        }
        else {
            throw  new NotFoundException(String.format("Autor com o id %d não encontrado", id));
        }
    }

}
