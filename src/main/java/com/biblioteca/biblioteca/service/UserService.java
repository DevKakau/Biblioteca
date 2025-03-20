package com.biblioteca.biblioteca.service;


import com.biblioteca.biblioteca.entities.User;
import com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // listar todos os usuarios
    public List<User> getAll(){
        return userRepository.findAll();
    }

    // Busca por id
    public Optional<User> GetById(Long id){
        return userRepository.findById(id);
    }

    // Criar um usuario no banco de dados
    public User createUser(User user){
        return userRepository.save(user);
    }

    // deletar um usuario do banco de dados atraves do ID
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
