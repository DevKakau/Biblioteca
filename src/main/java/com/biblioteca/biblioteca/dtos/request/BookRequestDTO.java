package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.entities.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookRequestDTO {

    private String nameBook;
    private Long authorId;
    private int yearPublication;


}
