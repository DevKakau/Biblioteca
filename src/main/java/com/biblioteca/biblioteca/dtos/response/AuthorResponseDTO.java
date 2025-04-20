package com.biblioteca.biblioteca.dtos.response;

import com.biblioteca.biblioteca.entities.Author;
import com.biblioteca.biblioteca.entities.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AuthorResponseDTO {

    private String nome;
    private String nacionalidade;
    private List<BookResumeDTO> livros;

    public AuthorResponseDTO(Author author){
        this.nome = author.getNome();
        this.nacionalidade = author.getNacionalidade();
        this.livros = author.getBooks().stream()
                .map(BookResumeDTO::new)
                .collect(Collectors.toList());
    }
}
