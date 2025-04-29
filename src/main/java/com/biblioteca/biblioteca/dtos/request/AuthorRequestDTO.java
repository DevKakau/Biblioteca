package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.entities.Author;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorRequestDTO {

    @NotBlank
    private final String name;
    @NotBlank
    private final String nationality;

    public Author toEntity(){
        Author author = new Author();
        author.setName(this.name);
        author.setNationality(this.nationality);
        return author;
    }

}
