package com.librarify.api.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.librarify.api.dtos.BookDTO;
import com.librarify.api.entities.Book;

public interface BookService {
    Book createBook(BookDTO bookDTO);

    List<Book> getAllBooks();

    Book getBookById(UUID id);

    Book updateBookById(UUID id, BookDTO bookDTO);

    void deleteBookById(UUID id);
}
