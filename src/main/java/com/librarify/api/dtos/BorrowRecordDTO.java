package com.librarify.api.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecordDTO {
    private UUID patronId;

    private UUID bookId;

    private LocalDateTime borrowedAt;

    private LocalDateTime returnedAt;

    public BorrowRecordDTO(UUID bookId, UUID patronId) {
        this.bookId = bookId;
        this.patronId = patronId;
    }
}
