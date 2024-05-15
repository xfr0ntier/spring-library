package com.librarify.api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.librarify.api.entities.Book;
import com.librarify.api.repositories.interfaces.BookRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateBook() {
        Book book = new Book("Mediocracy", "Alain Denaeult");

        Book savedBook = this.bookRepository.save(book);

        Assertions.assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    void testFindBookById() {
        Book book = this.bookRepository.save(new Book("Mediocracy", "Alain Denaeult"));

        Optional<Book> res = this.bookRepository.findById(book.getId());

        Assertions.assertThat(res.isPresent()).isTrue();
        Assertions.assertThat(res.get().getId()).isNotNull();
    }

    @Test
    void testFindAllBooks() {
        this.bookRepository.save(new Book("Mediocracy", "Alain Denaeult"));
        this.bookRepository.save(new Book("Mediocracy", "Alain Denaeult"));

        List<Book> books = this.bookRepository.findAll();

        Assertions.assertThat(books.size()).isEqualTo(2);
    }

    @Test
    void testDeleteBookById() {
        Book book = this.bookRepository.save(new Book("Mediocracy", "Alain Denaeult"));
        UUID bookId = book.getId();

        this.bookRepository.deleteById(bookId);

        Optional<Book> res = this.bookRepository.findById(bookId);

        Assertions.assertThat(res.isEmpty()).isTrue();
    }
}
