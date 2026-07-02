package com.suprun.datetimeapi;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * DateTimeApiOperations class demonstrates Java 8 Date-Time API features.
 * This class provides various operations using LocalDate, LocalTime, LocalDateTime, and ZonedDateTime.
 */
public class DateTimeApiOperations {

    /**
     * Get current date using LocalDate
     */
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Get current time using LocalTime
     */
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    /**
     * Get current date-time using LocalDateTime
     */
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Get current date-time in a specific zone using ZonedDateTime
     */
    public ZonedDateTime getCurrentZonedDateTime(String zoneId) {
        return ZonedDateTime.now(ZoneId.of(zoneId));
    }

    /**
     * Create LocalDate from year, month, and day
     */
    public LocalDate createDate(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    /**
     * Create LocalTime from hour, minute, and second
     */
    public LocalTime createTime(int hour, int minute, int second) {
        return LocalTime.of(hour, minute, second);
    }

    /**
     * Create LocalDateTime from year, month, day, hour, minute
     */
    public LocalDateTime createDateTime(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    /**
     * Parse date from string with default ISO format
     */
    public LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    /**
     * Parse date from string with custom format
     */
    public LocalDate parseDateWithFormat(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Parse time from string with default ISO format
     */
    public LocalTime parseTime(String timeString) {
        return LocalTime.parse(timeString);
    }

    /**
     * Format LocalDate to string
     */
    public String formatDate(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    /**
     * Format LocalDateTime to string
     */
    public String formatDateTime(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * Add days to LocalDate
     */
    public LocalDate addDays(LocalDate date, long days) {
        return date.plusDays(days);
    }

    /**
     * Add months to LocalDate
     */
    public LocalDate addMonths(LocalDate date, long months) {
        return date.plusMonths(months);
    }

    /**
     * Subtract days from LocalDate
     */
    public LocalDate subtractDays(LocalDate date, long days) {
        return date.minusDays(days);
    }

    /**
     * Get the day of week for a given date
     */
    public String getDayOfWeek(LocalDate date) {
        return date.getDayOfWeek().toString();
    }

    /**
     * Get the month name for a given date
     */
    public String getMonthName(LocalDate date) {
        return date.getMonth().toString();
    }

    /**
     * Check if a year is a leap year
     */
    public boolean isLeapYear(int year) {
        return Year.of(year).isLeap();
    }

    /**
     * Calculate the number of days between two dates
     */
    public long daysBetween(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2);
    }

    /**
     * Calculate the number of months between two dates
     */
    public long monthsBetween(LocalDate date1, LocalDate date2) {
        return ChronoUnit.MONTHS.between(date1, date2);
    }

    /**
     * Calculate the number of years between two dates
     */
    public long yearsBetween(LocalDate date1, LocalDate date2) {
        return ChronoUnit.YEARS.between(date1, date2);
    }

    /**
     * Get first day of month for a given date
     */
    public LocalDate getFirstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    /**
     * Get last day of month for a given date
     */
    public LocalDate getLastDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    /**
     * Compare two dates
     */
    public int compareDates(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2);
    }

    /**
     * Check if a date is before another date
     */
    public boolean isDateBefore(LocalDate date1, LocalDate date2) {
        return date1.isBefore(date2);
    }

    /**
     * Check if a date is after another date
     */
    public boolean isDateAfter(LocalDate date1, LocalDate date2) {
        return date1.isAfter(date2);
    }

    /**
     * Convert legacy Date to LocalDate
     */
    public LocalDate convertUtilDateToLocalDate(Date utilDate) {
        return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Convert LocalDate to legacy Date
     */
    public Date convertLocalDateToUtilDate(LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
