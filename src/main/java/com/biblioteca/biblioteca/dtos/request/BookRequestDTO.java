package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BookRequestDTO {

    private final String nameBook;
    private final Long authorId;
    private final int yearPublication;

}
