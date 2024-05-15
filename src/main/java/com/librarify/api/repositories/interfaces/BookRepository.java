package com.librarify.api.repositories.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarify.api.entities.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
