package com.librarify.api.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.librarify.api.dtos.PatronDTO;
import com.librarify.api.entities.Patron;

public interface PatronService {
    Patron createPatron(PatronDTO patronDTO);

    List<Patron> getAllPatrons();

    Patron getPatronById(UUID id);

    Patron updatePatronById(UUID id, PatronDTO patronDTO);

    void deletePatronById(UUID id);
}
