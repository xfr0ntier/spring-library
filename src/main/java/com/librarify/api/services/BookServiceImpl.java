package com.librarify.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.librarify.api.common.exceptions.ResourceNotFoundException;
import com.librarify.api.common.utils.AppUtils;
import com.librarify.api.dtos.BookDTO;
import com.librarify.api.entities.Book;
import com.librarify.api.repositories.interfaces.BookRepository;
import com.librarify.api.services.interfaces.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AppUtils appUtils;

    public BookServiceImpl(BookRepository bookRepository, AppUtils appUtils) {
        this.bookRepository = bookRepository;
        this.appUtils = appUtils;
    }

    @Override
    public Book createBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor());
        return this.bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getBookById(UUID id) {
        Optional<Book> res = this.bookRepository.findById(id);

        if (res.isPresent()) {
            return res.get();
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Book not found",
                new ResourceNotFoundException("Book not found"));
    }

    @Override
    public Book updateBookById(UUID id, BookDTO bookDTO) {
        Optional<Book> res = this.bookRepository.findById(id);

        if (res.isPresent()) {
            Book book = res.get();

            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setPubYear(bookDTO.getPubYear());
            book.setIsbn(bookDTO.getIsbn());
            book.setEdition(bookDTO.getEdition());
            book.setLanguage(bookDTO.getLanguage());
            book.setUpdatedAt(appUtils.convertLocalDateTimeToTimestamp(LocalDateTime.now()));

            return this.bookRepository.save(book);
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Book not found",
                new ResourceNotFoundException("Book not found"));
    }

    @Override
    public void deleteBookById(UUID id) {
        Optional<Book> res = this.bookRepository.findById(id);

        if (res.isPresent()) {
            this.bookRepository.deleteById(id);
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Book not found",
                new ResourceNotFoundException("Book not found"));
    }
}
