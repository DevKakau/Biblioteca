package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService service;

    @Operation(description = "Retorna uma lista com todos os livros  cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de livros"),
            @ApiResponse(responseCode = "404", description = "nenhum livro encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<Book> bookList(){
        return service.getAllBooks();
    }


    @Operation(description = "Retorna um livro especifico pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um livro"),
            @ApiResponse(responseCode = "404", description = "livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public Optional<Book> bookId(@PathVariable Long id){
        return service.getById(id);
    }



    @Operation(description = "Cria e armazena um livro com todas as informações no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return service.createBook(book);
    }


    @Operation(description = "Deleta um livro do banco de dados através do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "nenhum livro encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        service.deleteBook(id);
    }

}
