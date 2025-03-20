package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entities.User;
import com.biblioteca.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> userList(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> userId(@PathVariable Long id){
        return service.GetById(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User user){
        return service.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}
