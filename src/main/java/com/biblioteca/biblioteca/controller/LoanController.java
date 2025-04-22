package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.LoanRequestDTO;
import com.biblioteca.biblioteca.dtos.response.LoanResponseDTO;
import com.biblioteca.biblioteca.entities.Loan;
import com.biblioteca.biblioteca.exception.NotFoundException;
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

    private LoanService service;
    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> loanList(){
        List<LoanResponseDTO> loans = service.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> loanId(@PathVariable Long id){
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException(String.format("Emprestimo com o id %d não encontrado", id)));
    }


    @PatchMapping("/{id}")
    public void updateLoan(@PathVariable Long id){
        Optional<LoanResponseDTO> loan = service.getById(id);
        if(loan.isPresent()) {
            service.toggleLoanStatus(id);
        }
        else {
            throw new NotFoundException(String.format("Emprestimo com id %d não encontrado", id));
        }
    }


    @PostMapping
    public ResponseEntity<LoanResponseDTO> saveLoan(@RequestBody LoanRequestDTO loanRequestDTO){
        Loan loan = service.createLoan(loanRequestDTO);
        LoanResponseDTO responseDTO = new LoanResponseDTO(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id){
        Optional<LoanResponseDTO> loan = service.getById(id);
        if(loan.isPresent()){
            service.delete(id);
        }
        else {
            throw new NotFoundException(String.format("Emprestimo com o id %d não encontrado", id));
        }
    }
}
