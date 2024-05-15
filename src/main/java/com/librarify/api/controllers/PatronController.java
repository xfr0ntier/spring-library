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

import com.librarify.api.dtos.PatronDTO;
import com.librarify.api.entities.Patron;
import com.librarify.api.services.interfaces.PatronService;

@RestController
@RequestMapping("patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @PostMapping
    public Patron createPatron(@RequestBody PatronDTO patron) {
        return this.patronService.createPatron(patron);
    }

    @GetMapping
    public List<Patron> getAllPatrons() {
        return this.patronService.getAllPatrons();
    }

    @GetMapping("{id}")
    public Patron getPatronById(@PathVariable UUID id) {
        return this.patronService.getPatronById(id);
    }

    @PutMapping("{id}")
    public Patron updatePatronById(@PathVariable UUID id, @RequestBody PatronDTO patronDTO) {
        return this.patronService.updatePatronById(id, patronDTO);
    }

    @DeleteMapping("{id}")
    public void deletePatronById(@PathVariable UUID id) {
        this.patronService.deletePatronById(id);
    }
}
