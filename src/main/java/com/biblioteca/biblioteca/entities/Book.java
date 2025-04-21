package com.biblioteca.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;
    private  String nome;
    private  int anoPublicacao;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;


}
