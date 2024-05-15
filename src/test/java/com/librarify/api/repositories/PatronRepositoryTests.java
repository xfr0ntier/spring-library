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

import com.librarify.api.entities.Patron;
import com.librarify.api.repositories.interfaces.PatronRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PatronRepositoryTests {

    @Autowired
    private PatronRepository patronRepository;

    @Test
    void testCreatePatron() {
        Patron patron = new Patron("John", "+1 (23)-456-7890");

        Patron savedPatron = this.patronRepository.save(patron);

        Assertions.assertThat(savedPatron.getId()).isNotNull();
    }

    @Test
    void testFindPatronById() {
        Patron patron = this.patronRepository.save(new Patron("John", "+1 (23)-456-7890"));

        Optional<Patron> res = this.patronRepository.findById(patron.getId());

        Assertions.assertThat(res.isPresent()).isTrue();
        Assertions.assertThat(res.get().getId()).isNotNull();
    }

    @Test
    void testFindAllPatrons() {
        this.patronRepository.save(new Patron("John", "+1 (23)-456-7890"));
        this.patronRepository.save(new Patron("John", "+1 (23)-456-7890"));

        List<Patron> patrons = this.patronRepository.findAll();

        Assertions.assertThat(patrons.size()).isEqualTo(2);
    }

    @Test
    void testDeletePatronById() {
        Patron patron = this.patronRepository.save(new Patron("John", "+1 (23)-456-7890"));
        UUID patronId = patron.getId();

        this.patronRepository.deleteById(patronId);

        Optional<Patron> res = this.patronRepository.findById(patronId);

        Assertions.assertThat(res.isEmpty()).isTrue();
    }
}
