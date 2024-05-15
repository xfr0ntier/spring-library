package com.librarify.api.services;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.librarify.api.common.exceptions.ResourceNotFoundException;
import com.librarify.api.common.utils.AppUtils;
import com.librarify.api.dtos.BorrowRecordDTO;
import com.librarify.api.entities.BorrowRecord;
import com.librarify.api.repositories.interfaces.BorrowRecordRepository;
import com.librarify.api.services.interfaces.BookService;
import com.librarify.api.services.interfaces.BorrowRecordService;
import com.librarify.api.services.interfaces.PatronService;

import jakarta.persistence.NoResultException;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final PatronService patronService;
    private final BookService bookService;
    private final AppUtils appUtils;

    public BorrowRecordServiceImpl(
            BorrowRecordRepository borrowingRecordRepository,
            PatronService patronService,
            BookService bookService,
            AppUtils appUtils) {
        this.borrowRecordRepository = borrowingRecordRepository;
        this.patronService = patronService;
        this.bookService = bookService;
        this.appUtils = appUtils;
    }

    @Override
    public BorrowRecord registerBorrow(BorrowRecordDTO recordDTO) {
        if (this.patronService.getPatronById(recordDTO.getPatronId()) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Patron not found",
                    new ResourceNotFoundException("Patron not found"));
        }

        if (this.bookService.getBookById(recordDTO.getBookId()) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book not found",
                    new ResourceNotFoundException("Book not found"));
        }

        BorrowRecord record = new BorrowRecord();
        record.setBookId(recordDTO.getBookId());
        record.setPatronId(recordDTO.getPatronId());

        if (recordDTO.getBorrowedAt() == null) {
            record.setBorrowedAt(this.appUtils.convertLocalDateTimeToTimestamp(LocalDateTime.now()));
        } else {
            record.setBorrowedAt(this.appUtils.convertLocalDateTimeToTimestamp(recordDTO.getBorrowedAt()));
        }

        return this.borrowRecordRepository.createBorrowRecord(record);
    }

    @Override
    public BorrowRecord registerReturn(BorrowRecordDTO recordDTO) {
        try {
            BorrowRecord record = this.borrowRecordRepository.findPatronLatestBorrowRecord(
                    recordDTO.getBookId(),
                    recordDTO.getPatronId());

            if (recordDTO.getReturnedAt() != null) {
                record.setReturnedAt(
                        this.appUtils.convertLocalDateTimeToTimestamp(recordDTO.getReturnedAt()));
            } else {
                record.setReturnedAt(this.appUtils.convertLocalDateTimeToTimestamp(LocalDateTime.now()));
            }

            return this.borrowRecordRepository.updateBorrowRecord(record);

        } catch (NoResultException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Borrow record not found",
                    new ResourceNotFoundException("Borrow record not found"));
        }
    }
}
