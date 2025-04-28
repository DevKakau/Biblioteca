package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dtos.request.BookRequestDTO;
import com.biblioteca.biblioteca.dtos.response.BookResponseDTO;
import com.biblioteca.biblioteca.entities.Author;
import com.biblioteca.biblioteca.entities.Book;
import com.biblioteca.biblioteca.exception.ConflictException;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.repository.AuthorRepository;
import com.biblioteca.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookResponseDTO> listAll(){
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookResponseDTO::new)
                .collect(Collectors.toList());
    }


    public Optional<BookResponseDTO> getById(Long id){
        return bookRepository.findById(id)
                .map(BookResponseDTO::new);
    }


    public Book saveBy(BookRequestDTO bookDTO){
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new NotFoundException(String.format("O autor com o id %d não foi encontrado", bookDTO.getAuthorId())));

        if(!bookRepository.existsByName(bookDTO.getNameBook())){
            Book book = new Book();
            book.setName(bookDTO.getNameBook());
            book.setAuthor(author);
            book.setYearPublication(bookDTO.getYearPublication());
            return bookRepository.save(book);
        } else {
          throw  new ConflictException(String.format("O livro com o nome %s já foi cadastrado!!", bookDTO.getNameBook()));
        }
    }


    public void deleteBy(Long id){
         bookRepository.deleteById(id);
    }


}
