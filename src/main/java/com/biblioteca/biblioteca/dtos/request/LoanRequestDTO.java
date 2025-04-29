package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.enums.LoanStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LoanRequestDTO {

    @NotNull
    private final LocalDate loanDate;
    @NotNull
    private final LocalDate returnDate;
    @NotNull
    private final LoanStatus status;
    @NotNull
    private final Long userId;
    @NotNull
    private final Long bookId;

}
