package com.biblioteca.biblioteca.dtos.response;

import com.biblioteca.biblioteca.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDTO {
    private String nome;
    private String email;

    public UserResponseDTO(User user){
        this.nome = user.getNome();
        this.email = user.getEmail();
    }


}
