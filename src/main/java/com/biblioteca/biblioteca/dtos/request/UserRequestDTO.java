package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTO {

    private String nome;
    private String email;
    private String senha;


    public User toEntity(){
        User user = new User();
        user.setNome(this.nome);
        user.setEmail(this.email);
        user.setSenha(this.senha);
        return user;
    }
}
