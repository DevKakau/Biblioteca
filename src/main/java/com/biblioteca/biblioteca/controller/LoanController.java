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

    @GetMapping
    public List<LoanResponseDTO> loanList(){
        return service.getAllLoans();
    }


    @GetMapping("/{id}")
    public Optional<LoanResponseDTO> loanId(@PathVariable Long id){
        return service.getById(id);
    }


    @PutMapping("/{id}")
    public void updateLoan(@PathVariable Long id){
        service.toggleLoanStatus(id);
    }


    @PostMapping
    public ResponseEntity<LoanResponseDTO> saveLoan(@RequestBody LoanRequestDTO loanRequestDTO){
        Loan loan = service.createLoan(loanRequestDTO);
        LoanResponseDTO responseDTO = new LoanResponseDTO(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id){
        service.delete(id);
    }
}
