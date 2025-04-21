package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dtos.request.BookRequestDTO;
import com.biblioteca.biblioteca.dtos.response.BookResponseDTO;
import com.biblioteca.biblioteca.entities.Author;
import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.repository.AuthorRepository;
import com.biblioteca.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // Listar todos os livros
    public List<BookResponseDTO> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookResponseDTO::new)
                .collect(Collectors.toList());
    }

    // listar por id
    public Optional<BookResponseDTO> getById(Long id){
        return bookRepository.findById(id)
                .map(BookResponseDTO::new);
    }

    // salvar um novo livro no banco de dados
    public Book createBook(BookRequestDTO bookDTO){
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = new Book();
        book.setNome(bookDTO.getNameBook());
        book.setAuthor(author);
        book.setAnoPublicacao(bookDTO.getYearPublication());
        return bookRepository.save(book);
    }

    // deletar livro pelo id
    public void deleteBook(Long id){
         bookRepository.deleteById(id);
    }


}
