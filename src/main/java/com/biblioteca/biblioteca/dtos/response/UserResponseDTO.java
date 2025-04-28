package com.biblioteca.biblioteca.dtos.response;

import com.biblioteca.biblioteca.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserResponseDTO {
    private final String name;
    private final String email;

    public UserResponseDTO(User user){
        this.name = user.getName();
        this.email = user.getEmail();
    }


}
