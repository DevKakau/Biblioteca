package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dtos.request.AuthorRequestDTO;
import com.biblioteca.biblioteca.dtos.response.AuthorResponseDTO;
import com.biblioteca.biblioteca.dtos.response.UserResponseDTO;
import com.biblioteca.biblioteca.entities.Author;
import com.biblioteca.biblioteca.exception.ConflictException;
import com.biblioteca.biblioteca.repository.AuthorRepository;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    //Listar todos os autores da tabela
    public List<AuthorResponseDTO> getAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(AuthorResponseDTO::new)
                .collect(Collectors.toList());

    }

    //Buscar author pelo id
    public Optional<AuthorResponseDTO> getById(Long id){
        return authorRepository.findById(id)
                .map(AuthorResponseDTO::new);

    }

    //Salvar um autor no db
    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO){
            if (!authorRepository.existsByNome(authorRequestDTO.getNome())) {
                Author author = authorRequestDTO.toEntity();
                Author saved = authorRepository.save(author);
                return new AuthorResponseDTO(saved);
            }
            else {
                throw new ConflictException(String.format("Já existe um autor com o nome %s cadastrado no sistema", authorRequestDTO.getNome()));
            }

    }

    //Deletar um autor da tabela pelo id
    public void delete(Long id){
        authorRepository.deleteById(id);
    }
}
