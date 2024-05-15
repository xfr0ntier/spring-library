package com.librarify.api.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tbl_borrow_record")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    @Column(name = "patron_id", nullable = false)
    private UUID patronId;

    @Column(name = "borrowed_at")
    private Timestamp borrowedAt;

    @Column(name = "returned_at")
    private Timestamp returnedAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public BorrowRecord(UUID bookId, UUID patronId, LocalDateTime borrowedAt) {
        this.bookId = bookId;
        this.patronId = patronId;
        this.borrowedAt = Timestamp.from(borrowedAt.toInstant(null));
    }
}
