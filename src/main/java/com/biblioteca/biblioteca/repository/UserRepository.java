package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
