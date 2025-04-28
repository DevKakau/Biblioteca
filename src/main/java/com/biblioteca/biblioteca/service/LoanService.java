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

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<LoanResponseDTO> listAll(){
        List<Loan> loanList = loanRepository.findAll();
        return loanList.stream()
                .map(LoanResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<LoanResponseDTO> getById(Long id){
        return loanRepository.findById(id)
                .map(LoanResponseDTO::new);
    }


    public Loan saveBy(LoanRequestDTO dto) {

        Loan loan = new Loan();
        loan.setLoanDate(dto.getLoanDate());
        loan.setReturnDate(dto.getReturnDate());
        loan.setStatus(dto.getStatus());
        loan.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
        loan.setBook(bookRepository.findById(dto.getBookId()).orElseThrow());

        return loanRepository.save(loan);
    }


    public void toggleLoanStatus(Long id) {
        loanRepository.findById(id).ifPresent(loan -> {
            loan.setStatus(loan.getStatus() == LoanStatus.EM_ABERTO ? LoanStatus.DEVOLVIDO : LoanStatus.EM_ABERTO);
            loanRepository.save(loan);
        });
    }


    public  void deleteBy(Long id){
        loanRepository.deleteById(id);
    }

}
