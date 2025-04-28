package com.biblioteca.biblioteca.service;


import com.biblioteca.biblioteca.dtos.request.UserRequestDTO;
import com.biblioteca.biblioteca.dtos.response.UserResponseDTO;
import com.biblioteca.biblioteca.entities.User;
import com.biblioteca.biblioteca.exception.ConflictException;
import com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserResponseDTO> listAll(){
        List<User> users = repository.findAll();
        return users.stream()
                .map(UserResponseDTO::new) // usando construtor que recebe a entidade
                .collect(Collectors.toList());
    }


    public Optional<UserResponseDTO> GetById(Long id){
        return repository.findById(id)
                .map(UserResponseDTO::new);
    }

    public UserResponseDTO saveBy(UserRequestDTO userDto){
        if(!repository.existsByEmail(userDto.getEmail())){
            User user = userDto.toEntity();
            User saved = repository.save(user);
            return new UserResponseDTO(saved);
        }
        else {
            throw new ConflictException("Email invalido!!");
        }
    }

    public void deleteBy(Long id){
        repository.deleteById(id);
    }
}


