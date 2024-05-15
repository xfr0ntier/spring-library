package com.librarify.api.repositories;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.librarify.api.common.utils.AppUtils;
import com.librarify.api.entities.Book;
import com.librarify.api.entities.BorrowRecord;
import com.librarify.api.entities.Patron;
import com.librarify.api.repositories.interfaces.BookRepository;
import com.librarify.api.repositories.interfaces.BorrowRecordRepository;
import com.librarify.api.repositories.interfaces.PatronRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BorrowRecordRepositoryTests {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BorrowRecordRepository borrowRepository;

    @Test
    void testCreateBorrowRecord() {
        Book book = this.bookRepository.save(
                new Book("Mediocracy", "Alain Denaeult"));

        Patron patron = this.patronRepository.save(
                new Patron("John", "+1 (23)-456-7890"));

        Timestamp timestamp = this.appUtils.convertLocalDateTimeToTimestamp(LocalDateTime.now());

        BorrowRecord savedRecord = this.borrowRepository.createBorrowRecord(
                new BorrowRecord(book, patron, timestamp));

        Assertions.assertThat(savedRecord.getId()).isNotNull();
    }

    @Test
    void testUPdateBorrowRecord() {
        Book book = this.bookRepository.save(
                new Book("Mediocracy", "Alain Denaeult"));

        Patron patron = this.patronRepository.save(
                new Patron("John", "+1 (23)-456-7890"));

        Timestamp oldTimestamp = this.appUtils.convertLocalDateTimeToTimestamp(LocalDateTime.now());

        BorrowRecord record = this.borrowRepository.createBorrowRecord(
                new BorrowRecord(book, patron, oldTimestamp));

        Timestamp newTimestamp = this.appUtils.convertLocalDateTimeToTimestamp(LocalDateTime.now());

        record.setBorrowedAt(newTimestamp);

        BorrowRecord updatedRecord = this.borrowRepository.updateBorrowRecord(record);

        Assertions.assertThat(oldTimestamp).isNotEqualTo(newTimestamp);
        Assertions.assertThat(updatedRecord.getBorrowedAt()).isEqualTo(newTimestamp);
    }
}
