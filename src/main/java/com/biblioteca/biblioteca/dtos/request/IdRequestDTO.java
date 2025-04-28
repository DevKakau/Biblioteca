package com.biblioteca.biblioteca.dtos.request;

import lombok.Getter;

@Getter
public class IdRequestDTO {
    private final Long id;

    public IdRequestDTO(Long id) {
        this.id = id;
    }

}
