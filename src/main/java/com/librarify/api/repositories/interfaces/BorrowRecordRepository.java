package com.librarify.api.repositories.interfaces;

import java.util.UUID;

import com.librarify.api.entities.BorrowRecord;

import jakarta.persistence.NoResultException;

public interface BorrowRecordRepository {

    BorrowRecord createBorrowRecord(BorrowRecord record);

    BorrowRecord updateBorrowRecord(BorrowRecord record);

    BorrowRecord findPatronLatestBorrowRecord(UUID bookId, UUID patronId) throws NoResultException;
}
