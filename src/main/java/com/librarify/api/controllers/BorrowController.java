package com.librarify.api.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.librarify.api.dtos.BorrowRecordDTO;
import com.librarify.api.entities.BorrowRecord;
import com.librarify.api.services.interfaces.BorrowRecordService;

@RestController
public class BorrowController {

    private final BorrowRecordService borrowRecordService;

    public BorrowController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public BorrowRecord borrowBook(
            @PathVariable UUID bookId,
            @PathVariable UUID patronId,
            @RequestBody(required = false) BorrowRecordDTO recordDTO) {

        if (recordDTO == null) {
            recordDTO = new BorrowRecordDTO(bookId, patronId);
        } else {
            recordDTO.setBookId(bookId);
            recordDTO.setPatronId(patronId);
        }

        return this.borrowRecordService.registerBorrow(recordDTO);
    }

    @PutMapping("return/{bookId}/patron/{patronId}")
    public BorrowRecord returnBook(
            @PathVariable UUID bookId,
            @PathVariable UUID patronId,
            @RequestBody(required = false) BorrowRecordDTO recordDTO) {

        if (recordDTO == null) {
            recordDTO = new BorrowRecordDTO(bookId, patronId);
        } else {
            recordDTO.setBookId(bookId);
            recordDTO.setPatronId(patronId);
        }

        return this.borrowRecordService.registerReturn(recordDTO);
    }
}
