package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
