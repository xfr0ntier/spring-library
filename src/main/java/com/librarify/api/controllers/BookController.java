package com.librarify.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarify.api.dtos.BookDTO;
import com.librarify.api.entities.Book;
import com.librarify.api.services.interfaces.BookService;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestBody BookDTO bookDTO) {
        return this.bookService.createBook(bookDTO);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return this.bookService.getAllBooks();
    }

    @GetMapping("{id}")
    public Book getBookById(@PathVariable UUID id) {
        return this.bookService.getBookById(id);
    }

    @PutMapping("{id}")
    public Book updateBookById(@PathVariable UUID id, @RequestBody BookDTO bookDTO) {
        return this.bookService.updateBookById(id, bookDTO);
    }

    @DeleteMapping("{id}")
    public void deleteBookById(@PathVariable UUID id) {
        this.bookService.deleteBookById(id);
    }
}
