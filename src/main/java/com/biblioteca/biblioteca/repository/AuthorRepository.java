package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByName(String name);

}
