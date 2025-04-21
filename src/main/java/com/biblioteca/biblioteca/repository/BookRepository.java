package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

}
