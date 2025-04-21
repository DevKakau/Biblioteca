package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.UserRequestDTO;
import com.biblioteca.biblioteca.dtos.response.UserResponseDTO;
import com.biblioteca.biblioteca.entities.User;
import com.biblioteca.biblioteca.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<UserResponseDTO> userDTOS = service.getAll();
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> userId(@PathVariable Long id){
        return service.GetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userDTO){
        UserResponseDTO userResponseDTO = service.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}
