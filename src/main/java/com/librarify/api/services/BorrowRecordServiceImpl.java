package com.librarify.api.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.librarify.api.common.AppUtils;
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
            // TODO: throw Patron not found
        }

        if (this.bookService.getBookById(recordDTO.getBookId()) == null) {
            // TODO: throw Book not found
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
            // TODO: throw Borrow not found
            return null;
        }
    }
}
