package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Listar todos os livros
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // listar por id
    public Optional<Book> getById(Long id){
        return bookRepository.findById(id);
    }

    // salvar um novo livro no banco de dados
    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    // deletar livro pelo id
    public void deleteBook(Long id){
         bookRepository.deleteById(id);
    }


}
