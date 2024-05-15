package com.librarify.api.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class AppUtils {

    public Timestamp convertLocalDateTimeToTimestamp(LocalDateTime ldt) {
        ZoneOffset zoneOffset = ZoneId.of(TimeZone.getDefault().getID()).getRules().getOffset(LocalDateTime.now());

        return Timestamp.from(ldt.toInstant(zoneOffset));
    }
}
