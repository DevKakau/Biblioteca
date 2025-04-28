package com.biblioteca.biblioteca.dtos.response;

import com.biblioteca.biblioteca.entities.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthorResponseDTO {

    private final String name;
    private final String nationality;
    private final List<BookResponseDTO> livros;

    public AuthorResponseDTO(Author author){
        this.name = author.getName();
        this.nationality = author.getNationality();
        this.livros = author.getBooks().stream()
                .map(BookResponseDTO::new)
                .collect(Collectors.toList());
    }
}
