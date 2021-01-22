package de.thkoeln.inf.gpm.vgb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    public static final SimpleDateFormat CAMUNDA_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * @param date Date in the format "yyyy-MM-dd'T'HH:mm:ss"
     */
    public static Date parseDate(String date) throws ParseException {
        return CAMUNDA_DATE_FORMAT.parse(date);
    }

    public static String toString(Date date) {
        return CAMUNDA_DATE_FORMAT.format(date);
    }

    public static String toString(LocalDate date) {
        return CAMUNDA_DATE_FORMAT.format(toDate(date));
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Integer calcAge(String birthdate) throws ParseException {
        return calcAge(parseDate(birthdate));
    }

    public static Integer calcAge(Date birthdate) {
        //java.util.Date --> java.time.LocalDateTime
        LocalDateTime gLocDT = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        //java.time.LocalDateTime --> LocalDate
        LocalDate gLocD = gLocDT.toLocalDate();

        return Period.between(gLocD, LocalDate.now()).getYears();
    }
}
