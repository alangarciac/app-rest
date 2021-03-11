package com.app.rest.format;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormat {

    static final String PATTERN = "dd-MM-yyyy HH:mm";

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
    public static LocalDateTime fromEpoch(Long epochValue) { //Of Milliseconds
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochValue), ZoneId.systemDefault());
    }

    /**
     * Transform LocalDateTime to Epoch Long
     * */
    public static Long toEpoch(LocalDateTime localDateTime) { //In Milliseconds
        return  localDateTime.toEpochSecond(ZoneId.of(ZoneId.systemDefault().getId()).getRules().getOffset(Instant.now()))*1000;


    }

    public static LocalDateTime now(){
        return LocalDateTime.now(ZoneId.of(ZoneId.systemDefault().getId()).getRules().getOffset(Instant.now()));
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
