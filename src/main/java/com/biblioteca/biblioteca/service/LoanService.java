package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dtos.request.LoanRequestDTO;
import com.biblioteca.biblioteca.dtos.response.LoanResponseDTO;
import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.entities.User;
import com.biblioteca.biblioteca.enums.LoanStatus;
import com.biblioteca.biblioteca.entities.Loan;
import com.biblioteca.biblioteca.exception.ConflictException;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.repository.BookRepository;
import com.biblioteca.biblioteca.repository.LoanRepository;
import com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private LoanRepository loanRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    // Listar todos os emprestismos
    public List<LoanResponseDTO> getAllLoans(){
        List<Loan> loanList = loanRepository.findAll();
        return loanList.stream()
                .map(LoanResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Busca emprestimo pelo id
    public Optional<LoanResponseDTO> getById(Long id){
        return loanRepository.findById(id)
                .map(LoanResponseDTO::new);
    }

    // Salvar um emprestimo no db
    public Loan createLoan(LoanRequestDTO dto) {

        Loan loan = new Loan();
        loan.setDataEmprestimo(dto.getDataEmprestimo());
        loan.setDataDevolucao(dto.getDataDevolucao());
        loan.setStatus(dto.getStatus());
        loan.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
        loan.setBook(bookRepository.findById(dto.getBookId()).orElseThrow());

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
