package com.app.rest.format;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormat {

    static final String PATTERN = "dd-MM-yyyy HH:mm";
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

    /**
     * Transform Java Date to LocalDateTime
     * */
    @Deprecated
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * Print LocalDateTime beauty in format dd-MM-yyyy HH:mm
     * */
    public static String printBeauty(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Transform Long Epoch to LocalDateTime
     * */
    public static LocalDateTime fromEpoch(Long epochValue) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochValue), ZoneId.systemDefault());
    }

    /**
     * Transform LocalDateTime to Epoch Long
     * */
    public static Long toEpoch(LocalDateTime localDateTime) {
        return  localDateTime.toEpochSecond(ZoneOffset.of(ZoneId.systemDefault().toString()));
    }


    /**
     * Transform String with format dd-MM-yyyy HH:mm to LocalDateTime
     * */
    public static LocalDateTime fromString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date.atStartOfDay(ZoneId.systemDefault()).toLocalDateTime();
    }

}
