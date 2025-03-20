package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.entities.Author;
import com.biblioteca.biblioteca.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    //Listar todos os autores da tabela
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    //Buscar author pelo id
    public Optional<Author> getById(Long id){
        return authorRepository.findById(id);
    }

    //Salvar um autor no db
    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }

    //Deletar um autor da tabela pelo id
    public void delete(Long id){
        authorRepository.deleteById(id);
    }
}
