package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.enums.LoanStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LoanRequestDTO {

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LoanStatus status;
    private Long userId;
    private Long bookId;

}
