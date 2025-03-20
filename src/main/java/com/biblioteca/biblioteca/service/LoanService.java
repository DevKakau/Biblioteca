package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.enums.LoanStatus;
import com.biblioteca.biblioteca.entities.Loan;
import com.biblioteca.biblioteca.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    // Listar todos os emprestismos
    public List<Loan> getAllLoans(){
        return loanRepository.findAll();
    }

    // Busca emprestimo pelo id
    public Optional<Loan> getById(Long id){
        return loanRepository.findById(id);
    }

    // Salvar um emprestimo no db
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    // atualizar o status do emprestimo
    public void toggleLoanStatus(Long id) {
        loanRepository.findById(id).ifPresent(loan -> {
            loan.setStatus(loan.getStatus() == LoanStatus.EM_ABERTO ? LoanStatus.DEVOLVIDO : LoanStatus.EM_ABERTO);
            loanRepository.save(loan);
        });
    }

    // deletar emprestimo do db pelo id
    public  void delete(Long id){
        loanRepository.deleteById(id);
    }

}
