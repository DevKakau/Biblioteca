package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.UserRequestDTO;
import com.biblioteca.biblioteca.dtos.response.UserResponseDTO;
import com.biblioteca.biblioteca.entities.User;
import com.biblioteca.biblioteca.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    @Operation(description = "Retorna uma lista de usuarios cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de usuarios"),
            @ApiResponse(responseCode = "404", description = "nenhum usuario encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<UserResponseDTO> userDTOS = service.getAll();
        return ResponseEntity.ok(userDTOS);
    }


    @Operation(description = "Retorna um usuario especifico através do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um usuario"),
            @ApiResponse(responseCode = "404", description = "nenhum usuario encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> userId(@PathVariable Long id){
        return service.GetById(id)
                .map(user -> ResponseEntity.ok(new UserResponseDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(description = "Cria e armazena um usuario com todas as informações no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userDTO){
        User user = service.createUser(userDTO);
        return ResponseEntity.ok(new UserResponseDTO(user));

    }


    @Operation(description = "Deleta um usuario especifico do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "nenhum usuario encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}
