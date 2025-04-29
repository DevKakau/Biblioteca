package com.biblioteca.biblioteca.dtos.request;

import com.biblioteca.biblioteca.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank
    private final String name;
    @NotBlank @Email
    private final String email;
    @NotBlank
    private final String password;


    public User toEntity(){
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        return user;
    }
}
