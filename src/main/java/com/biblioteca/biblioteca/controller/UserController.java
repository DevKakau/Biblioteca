package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.IdRequestDTO;
import com.biblioteca.biblioteca.dtos.request.UserRequestDTO;
import com.biblioteca.biblioteca.dtos.response.UserResponseDTO;
import com.biblioteca.biblioteca.entities.User;
import com.biblioteca.biblioteca.exception.NotFoundException;
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
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll(){
        List<UserResponseDTO> userDTOS = service.listAll();
        return ResponseEntity.ok(userDTOS);
    }

    @PostMapping("/id")
    public ResponseEntity<UserResponseDTO> getBy(@RequestBody IdRequestDTO request){
        Long id = request.getId();
        return service.GetById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException(String.format("Usuario com o id ['%d'] não encontrado", id)));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveBy(@RequestBody UserRequestDTO userDTO){
        UserResponseDTO userResponseDTO = service.saveBy(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id){
        Optional<UserResponseDTO> user = service.GetById(id);
        if(user.isPresent()){
            service.deleteBy(id);
        }
        else {
            throw new NotFoundException(String.format("Usuario com o id ['%d'] não encontrado", id));
        }

    }
}
