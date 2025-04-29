package com.biblioteca.biblioteca.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
;

@Getter
@AllArgsConstructor
public class BookRequestDTO {

    @NotBlank
    private final String nameBook;
    @NotNull
    private final Long authorId;
    @Min(value = 1500) @Max(value = 2025)
    private final int yearPublication;

}
