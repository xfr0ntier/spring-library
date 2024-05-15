package com.librarify.api.repositories.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarify.api.entities.Patron;

public interface PatronRepository extends JpaRepository<Patron, UUID> {
}
