package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorRequestDTO {

    private final String name;
    private final String nationality;

    public Author toEntity(){
        Author author = new Author();
        author.setName(this.name);
        author.setNationality(this.nationality);
        return author;
    }

}
