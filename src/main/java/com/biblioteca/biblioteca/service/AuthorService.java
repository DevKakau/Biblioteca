package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dtos.request.AuthorRequestDTO;
import com.biblioteca.biblioteca.dtos.response.AuthorResponseDTO;
import com.biblioteca.biblioteca.entities.Author;
import com.biblioteca.biblioteca.exception.ConflictException;
import com.biblioteca.biblioteca.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<AuthorResponseDTO> listAll(){
        List<Author> authors = repository.findAll();
        return authors.stream()
                .map(AuthorResponseDTO::new)
                .collect(Collectors.toList());

    }

    public Optional<AuthorResponseDTO> getById(Long id){
        return repository.findById(id)
                .map(AuthorResponseDTO::new);

    }

    public AuthorResponseDTO saveBy(AuthorRequestDTO authorRequestDTO){

        final String name = Optional.ofNullable(authorRequestDTO)
                .map(AuthorRequestDTO::getName)
                .orElseThrow(() -> new IllegalArgumentException("Author name cannot be null"));

            if (!repository.existsByName(name)) {
                Author author = authorRequestDTO.toEntity();
                Author saved = repository.save(author);
                return new AuthorResponseDTO(saved);
            }
            else {
                throw new ConflictException(String.format("Já existe um autor com o nome [%s] cadastrado no sistema", authorRequestDTO.getName()));
            }
    }

    public void deleteBy(Long id){
        repository.deleteById(id);
    }
}
