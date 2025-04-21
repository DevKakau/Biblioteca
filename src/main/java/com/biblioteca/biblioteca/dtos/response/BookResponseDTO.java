package com.biblioteca.biblioteca.dtos.response;

import com.biblioteca.biblioteca.entities.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookResponseDTO {

    private Long id;
    private String name;
    private String nameAuthor;
    private int yearPublication;

    public BookResponseDTO(Book book){
        this.id = book.getId();
        this.name = book.getNome();
        this.nameAuthor = book.getAuthor().getNome();
        this.yearPublication = book.getAnoPublicacao();
    }

}
