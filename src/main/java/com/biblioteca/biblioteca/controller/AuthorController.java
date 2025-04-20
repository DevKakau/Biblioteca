package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entities.Author;
import com.biblioteca.biblioteca.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @Operation(description = "Retorna uma lista com todos os Autores que estão cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de autores"),
            @ApiResponse(responseCode = "404", description = "nenhum autor encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @GetMapping
    public List<Author> authorList(){
        return service.getAllAuthors();
    }
    @Operation(description = "Busca um autor pelo id dele")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um autor especifico atraves do id"),
            @ApiResponse(responseCode = "404", description = "não existe autor com esse id"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @GetMapping("/{id}")
    public Optional<Author> authorId(@PathVariable Long id){
        return service.getById(id);
    }


    @Operation(description = "Cria um author com todas as inforções e armazena ao banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor criado e adicionado com sucesso "),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public Author saveAuthor(@RequestBody Author author){
        return service.createAuthor(author);
    }



    @Operation(description = "Deleta um autor do banco através do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor foi deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "não existe autor com esse id"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        service.delete(id);
    }


}
