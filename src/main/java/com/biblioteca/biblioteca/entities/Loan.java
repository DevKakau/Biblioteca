package com.biblioteca.biblioteca.entities;

import com.biblioteca.biblioteca.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "TB_LOAN")
public class Loan {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private LocalDate dataEmprestimo;

    @Getter
    @Setter
    private LocalDate dataDevolucao;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private LoanStatus status;


    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public Loan(){

    }


}
