package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dtos.request.IdRequestDTO;
import com.biblioteca.biblioteca.dtos.request.LoanRequestDTO;
import com.biblioteca.biblioteca.dtos.response.LoanResponseDTO;
import com.biblioteca.biblioteca.entities.Loan;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService service;
    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> listAll(){
        List<LoanResponseDTO> loans = service.listAll();
        return ResponseEntity.ok(loans);
    }

    @PostMapping("/id")
    public ResponseEntity<LoanResponseDTO> getBy(@RequestBody IdRequestDTO request){
        Long id = request.getId();
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException(String.format("Emprestimo com o id ['%d'] não encontrado", id)));
    }

    @PatchMapping("/{id}")
    public void updateBy(@PathVariable Long id){
        Optional<LoanResponseDTO> loan = service.getById(id);
        if(loan.isPresent()) {
            service.toggleLoanStatus(id);
        }
        else {
            throw new NotFoundException(String.format("Emprestimo com o id ['%d'] não encontrado", id));
        }
    }


    @PostMapping
    public ResponseEntity<LoanResponseDTO> saveBy(@RequestBody LoanRequestDTO loanRequestDTO){
        Loan loan = service.saveBy(loanRequestDTO);
        LoanResponseDTO responseDTO = new LoanResponseDTO(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id){
        Optional<LoanResponseDTO> loan = service.getById(id);
        if(loan.isPresent()){
            service.deleteBy(id);
        }
        else {
            throw new NotFoundException(String.format("Autor com o id ['%d'] não encontrado", id));
        }
    }
}
