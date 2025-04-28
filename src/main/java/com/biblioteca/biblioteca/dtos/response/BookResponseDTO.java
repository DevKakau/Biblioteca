package com.biblioteca.biblioteca.dtos.response;

import com.biblioteca.biblioteca.entities.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BookResponseDTO {

    private final Long id;
    private final String name;
    private final String nameAuthor;
    private final int yearPublication;

    public BookResponseDTO(Book book){
        this.id = book.getId();
        this.name = book.getName();
        this.nameAuthor = book.getAuthor().getName();
        this.yearPublication = book.getYearPublication();
    }

}
