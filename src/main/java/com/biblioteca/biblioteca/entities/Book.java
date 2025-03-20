package com.biblioteca.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_BOOK")
public class Book {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Getter
    @Setter

    private String nome;
    @Getter
    @Setter

    private int anoPublicacao;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;


    public Book(){

    }

    public Book(Long id, String nome, int anoPublicacao) {
        this.id = id;
        this.nome = nome;
        this.anoPublicacao = anoPublicacao;
    }


}
