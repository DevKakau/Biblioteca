package com.biblioteca.biblioteca.dtos.response;

import com.biblioteca.biblioteca.entities.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookResumeDTO {

    private Long id;
    private String nome;

    public BookResumeDTO(Book book){
        this.id = book.getId();
        this.nome = book.getNome();
    }

}
