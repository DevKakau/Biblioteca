package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dtos.request.LoanRequestDTO;
import com.biblioteca.biblioteca.dtos.response.LoanResponseDTO;
import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.entities.User;
import com.biblioteca.biblioteca.enums.LoanStatus;
import com.biblioteca.biblioteca.entities.Loan;
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
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

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
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Loan loan = new Loan();
        loan.setDataEmprestimo(dto.getDataEmprestimo());
        loan.setDataDevolucao(dto.getDataDevolucao());
        loan.setStatus(dto.getStatus());
        loan.setUser(user);
        loan.setBook(book);

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
