package com.biblioteca.biblioteca.dtos.response;

import com.biblioteca.biblioteca.entities.Loan;
import com.biblioteca.biblioteca.enums.LoanStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter

public class LoanResponseDTO {

    private final LocalDate loanDate;
    private final LocalDate returnDate;
    private final LoanStatus status;
    private final String nameUser;
    private final String nameBook;

    public LoanResponseDTO(Loan loan){
        this.loanDate = loan.getLoanDate();
        this.returnDate = loan.getReturnDate();
        this.status = loan.getStatus();
        this.nameUser = loan.getUser().getName();
        this.nameBook = loan.getBook().getName();
    }
}
