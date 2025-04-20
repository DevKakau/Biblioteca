package com.biblioteca.biblioteca.service;


import com.biblioteca.biblioteca.dtos.request.UserRequestDTO;
import com.biblioteca.biblioteca.dtos.response.UserResponseDTO;
import com.biblioteca.biblioteca.entities.User;
import com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // listar todos os usuarios
    public List<UserResponseDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponseDTO::new) // usando construtor que recebe a entidade
                .collect(Collectors.toList());
    }


    // Busca por id
    public Optional<UserResponseDTO> GetById(Long id){
        return userRepository.findById(id)
                .map(UserResponseDTO::new);
    }

    // Criar um usuario no banco de dados
    public UserResponseDTO createUser(UserRequestDTO userDto){
        User user = userDto.toEntity();
        User saved = userRepository.save(user);
        return new UserResponseDTO(saved);

    }

    // deletar um usuario do banco de dados atraves do ID
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}


