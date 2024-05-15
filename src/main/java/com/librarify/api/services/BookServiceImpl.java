package com.librarify.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.librarify.api.common.AppUtils;
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

        // TODO: throw Book not found

        return null;
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

        // TODO: throw Book not found
        return null;
    }

    @Override
    public void deleteBookById(UUID id) {
        Optional<Book> res = this.bookRepository.findById(id);

        if (res.isPresent()) {
            this.bookRepository.deleteById(id);
        }

        // TODO: throw Book not found
    }
}
