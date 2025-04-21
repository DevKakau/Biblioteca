package com.biblioteca.biblioteca.dtos.response;

import com.biblioteca.biblioteca.entities.Loan;
import com.biblioteca.biblioteca.enums.LoanStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LoanResponseDTO {

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LoanStatus status;
    private String nameUser;
    private String nameBook;

    public LoanResponseDTO(Loan loan){
        this.dataEmprestimo = loan.getDataEmprestimo();
        this.dataDevolucao = loan.getDataDevolucao();
        this.status = loan.getStatus();
        this.nameUser = loan.getUser().getNome();
        this.nameBook = loan.getBook().getNome();
    }
}
