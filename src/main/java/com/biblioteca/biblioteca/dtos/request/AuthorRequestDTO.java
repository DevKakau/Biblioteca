package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.entities.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestDTO {

    private String nome;
    private String nacionalidade;

    public Author toEntity(){
        Author author = new Author();
        author.setNome(this.nome);
        author.setNacionalidade(this.nacionalidade);
        return author;
    }

}
