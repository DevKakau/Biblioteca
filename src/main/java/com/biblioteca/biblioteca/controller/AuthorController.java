package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.AuthorRequestDTO;
import com.biblioteca.biblioteca.dtos.request.IdRequestDTO;
import com.biblioteca.biblioteca.dtos.response.AuthorResponseDTO;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> listAll(){
        List<AuthorResponseDTO> authorDTOS = service.listAll();
        return ResponseEntity.ok(authorDTOS);
    }

    @PostMapping("/id")
    public ResponseEntity<AuthorResponseDTO> getBy(@RequestBody IdRequestDTO request){
        Long id = request.getId();
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException(String.format("Autor com o id ['%d'] não encontrado", id)));
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> saveBy(@RequestBody @Valid AuthorRequestDTO authorRequestDTO){
        AuthorResponseDTO authorResponseDTO = service.saveBy(authorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id){
        Optional<AuthorResponseDTO> author = service.getById(id);
        if (author.isPresent()){
            service.deleteBy(id);
        }
        else {
            throw  new NotFoundException(String.format("Autor com o id ['%d'] não encontrado", id));
        }
    }

}
