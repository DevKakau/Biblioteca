package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

}
