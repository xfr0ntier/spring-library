package com.librarify.api.services.interfaces;

import com.librarify.api.dtos.BorrowRecordDTO;
import com.librarify.api.entities.BorrowRecord;

public interface BorrowRecordService {

    BorrowRecord registerBorrow(BorrowRecordDTO recordDTO);

    BorrowRecord registerReturn(BorrowRecordDTO recordDTO);
}
