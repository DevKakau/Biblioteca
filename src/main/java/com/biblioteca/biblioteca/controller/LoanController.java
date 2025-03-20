package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entities.Loan;
import com.biblioteca.biblioteca.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @GetMapping
    public List<Loan> loanList(){
        return service.getAllLoans();
    }

    @GetMapping("/{id}")
    public Optional<Loan> loanId(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public void updateLoan(@PathVariable Long id){
        service.toggleLoanStatus(id);
    }

    @PostMapping
    public Loan saveLoan(@RequestBody Loan loan){
        return service.createLoan(loan);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id){
        service.delete(id);
    }
}
