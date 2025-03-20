package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
