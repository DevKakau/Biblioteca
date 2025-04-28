package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LoanRequestDTO {

    private final LocalDate loanDate;
    private final LocalDate returnDate;
    private final LoanStatus status;
    private final Long userId;
    private final Long bookId;

}
