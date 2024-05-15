package com.librarify.api.services;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.librarify.api.dtos.BookDTO;
import com.librarify.api.entities.Book;
import com.librarify.api.services.interfaces.BookService;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookServiceImplTests {

    @Autowired
    private BookService bookService;

    @Test
    void testCreateBook() {
        BookDTO bookDTO = new BookDTO("Mediocracy", "Alain Denaeult");

        Book savedBook = this.bookService.createBook(bookDTO);

        Assertions.assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    void testGetBookById() {
        Book book = this.bookService.createBook(new BookDTO("Mediocracy", "Alain Denaeult"));

        Book foundBook = this.bookService.getBookById(book.getId());

        Assertions.assertThat(foundBook).isNotNull();
    }

    @Test
    void testGetAllBooks() {
        this.bookService.createBook(new BookDTO("Mediocracy", "Alain Denaeult"));
        this.bookService.createBook(new BookDTO("Mediocracy", "Alain Denaeult"));

        List<Book> books = this.bookService.getAllBooks();

        Assertions.assertThat(books.size()).isEqualTo(2);
    }

    // TODO: testDeleteBookById()
}
