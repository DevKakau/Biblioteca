package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO {

    private final String name;
    private final String email;
    private final String password;


    public User toEntity(){
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        return user;
    }
}
