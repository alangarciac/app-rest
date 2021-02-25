package com.app.rest.format;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormat {

    public static LocalDateTime toLocalTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static String printBeauty(Date date){
        return toLocalTime(date).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
