package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.LoanRequestDTO;
import com.biblioteca.biblioteca.dtos.response.LoanResponseDTO;
import com.biblioteca.biblioteca.entities.Loan;
import com.biblioteca.biblioteca.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @Operation(description = "Retorna uma lista com todos os emprestimos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista com os emprestimos"),
            @ApiResponse(responseCode = "404", description = "nenhum emprestimo encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<LoanResponseDTO> loanList(){
        return service.getAllLoans();
    }



    @Operation(description = "Retorna um emprestimo especifico através do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um emprestimo"),
            @ApiResponse(responseCode = "404", description = "nenhum emprestimo encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public Optional<LoanResponseDTO> loanId(@PathVariable Long id){
        return service.getById(id);
    }


    @Operation(description = "Atualiza o status do emprestimo de em_aberto pra devolvido ou devolvido pra em_aberto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O status do emprestimo foi atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "dados invalidos / requisição mal formada"),
            @ApiResponse(responseCode = "404", description = "nenhum emprestimo encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public void updateLoan(@PathVariable Long id){
        service.toggleLoanStatus(id);
    }


    @Operation(description = "Cria e armazena um emprestimo no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna uma lista de livros"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<LoanResponseDTO> saveLoan(@RequestBody LoanRequestDTO loanRequestDTO){
        Loan loan = service.createLoan(loanRequestDTO);
        LoanResponseDTO responseDTO = new LoanResponseDTO(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }



    @Operation(description = "Deleta um emprestimo do banco de dados através do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emprestimo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "nenhum emprestimo encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id){
        service.delete(id);
    }
}
