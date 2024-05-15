package com.librarify.api.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.librarify.api.entities.BorrowRecord;
import com.librarify.api.repositories.interfaces.BorrowRecordRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class BorrowRecordRepositoryImpl implements BorrowRecordRepository {
    private final EntityManager em;

    public BorrowRecordRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public BorrowRecord createBorrowRecord(BorrowRecord record) {

        this.em.persist(record);

        return this.em.find(BorrowRecord.class, record.getId());
    }

    @Override
    @Transactional
    public BorrowRecord updateBorrowRecord(BorrowRecord record) {
        return this.em.merge(record);
    }

    @Override
    public BorrowRecord findPatronLatestBorrowRecord(UUID bookId, UUID patronId) {
        TypedQuery<BorrowRecord> selectQuery = this.em.createQuery("""
                SELECT b
                FROM BorrowRecord b
                WHERE b.bookId = :bookId AND b.patronId = :patronId
                AND b.borrowedAt IN (
                    SELECT MAX(mb.borrowedAt)
                    FROM BorrowRecord mb
                    WHERE mb.bookId = :bookId AND mb.patronId = :patronId
                )
                """,
                BorrowRecord.class);

        selectQuery.setParameter("bookId", bookId);
        selectQuery.setParameter("patronId", patronId);

        return selectQuery.getSingleResult();
    }

}
