package com.suprun.datetimeapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DateTimeApiOperations Tests")
public class DateTimeApiOperationsTest {

    private DateTimeApiOperations operations;

    @BeforeEach
    public void setUp() {
        operations = new DateTimeApiOperations();
    }

    @Test
    @DisplayName("Test getting current date")
    public void testGetCurrentDate() {
        LocalDate currentDate = operations.getCurrentDate();
        assertNotNull(currentDate);
        assertEquals(LocalDate.now(), currentDate);
    }

    @Test
    @DisplayName("Test getting current time")
    public void testGetCurrentTime() {
        LocalTime currentTime = operations.getCurrentTime();
        assertNotNull(currentTime);
    }

    @Test
    @DisplayName("Test getting current date-time")
    public void testGetCurrentDateTime() {
        LocalDateTime currentDateTime = operations.getCurrentDateTime();
        assertNotNull(currentDateTime);
    }

    @Test
    @DisplayName("Test getting current zoned date-time")
    public void testGetCurrentZonedDateTime() {
        ZonedDateTime zonedDateTime = operations.getCurrentZonedDateTime("America/New_York");
        assertNotNull(zonedDateTime);
    }

    @Test
    @DisplayName("Test creating date")
    public void testCreateDate() {
        LocalDate date = operations.createDate(2024, 6, 15);
        assertEquals(2024, date.getYear());
        assertEquals(6, date.getMonthValue());
        assertEquals(15, date.getDayOfMonth());
    }

    @Test
    @DisplayName("Test creating time")
    public void testCreateTime() {
        LocalTime time = operations.createTime(14, 30, 45);
        assertEquals(14, time.getHour());
        assertEquals(30, time.getMinute());
        assertEquals(45, time.getSecond());
    }

    @Test
    @DisplayName("Test creating date-time")
    public void testCreateDateTime() {
        LocalDateTime dateTime = operations.createDateTime(2024, 6, 15, 14, 30);
        assertEquals(2024, dateTime.getYear());
        assertEquals(6, dateTime.getMonthValue());
        assertEquals(15, dateTime.getDayOfMonth());
        assertEquals(14, dateTime.getHour());
        assertEquals(30, dateTime.getMinute());
    }

    @Test
    @DisplayName("Test parsing date")
    public void testParseDate() {
        LocalDate date = operations.parseDate("2024-06-15");
        assertEquals(2024, date.getYear());
        assertEquals(6, date.getMonthValue());
        assertEquals(15, date.getDayOfMonth());
    }

    @Test
    @DisplayName("Test parsing date with custom format")
    public void testParseDateWithFormat() {
        LocalDate date = operations.parseDateWithFormat("15/06/2024", "dd/MM/yyyy");
        assertEquals(2024, date.getYear());
        assertEquals(6, date.getMonthValue());
        assertEquals(15, date.getDayOfMonth());
    }

    @Test
    @DisplayName("Test parsing time")
    public void testParseTime() {
        LocalTime time = operations.parseTime("14:30:45");
        assertEquals(14, time.getHour());
        assertEquals(30, time.getMinute());
        assertEquals(45, time.getSecond());
    }

    @Test
    @DisplayName("Test formatting date")
    public void testFormatDate() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        String formatted = operations.formatDate(date, "dd/MM/yyyy");
        assertEquals("15/06/2024", formatted);
    }

    @Test
    @DisplayName("Test formatting date-time")
    public void testFormatDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 6, 15, 14, 30, 45);
        String formatted = operations.formatDateTime(dateTime, "dd/MM/yyyy HH:mm:ss");
        assertEquals("15/06/2024 14:30:45", formatted);
    }

    @Test
    @DisplayName("Test adding days to date")
    public void testAddDays() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        LocalDate result = operations.addDays(date, 5);
        assertEquals(20, result.getDayOfMonth());
    }

    @Test
    @DisplayName("Test adding months to date")
    public void testAddMonths() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        LocalDate result = operations.addMonths(date, 3);
        assertEquals(9, result.getMonthValue());
    }

    @Test
    @DisplayName("Test subtracting days from date")
    public void testSubtractDays() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        LocalDate result = operations.subtractDays(date, 5);
        assertEquals(10, result.getDayOfMonth());
    }

    @Test
    @DisplayName("Test getting day of week")
    public void testGetDayOfWeek() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        String dayOfWeek = operations.getDayOfWeek(date);
        assertEquals("SATURDAY", dayOfWeek);
    }

    @Test
    @DisplayName("Test getting month name")
    public void testGetMonthName() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        String monthName = operations.getMonthName(date);
        assertEquals("JUNE", monthName);
    }

    @Test
    @DisplayName("Test leap year check")
    public void testIsLeapYear() {
        assertTrue(operations.isLeapYear(2024));
        assertFalse(operations.isLeapYear(2023));
        assertTrue(operations.isLeapYear(2000));
        assertFalse(operations.isLeapYear(1900));
    }

    @Test
    @DisplayName("Test days between two dates")
    public void testDaysBetween() {
        LocalDate date1 = LocalDate.of(2024, 6, 15);
        LocalDate date2 = LocalDate.of(2024, 6, 20);
        long daysBetween = operations.daysBetween(date1, date2);
        assertEquals(5, daysBetween);
    }

    @Test
    @DisplayName("Test months between two dates")
    public void testMonthsBetween() {
        LocalDate date1 = LocalDate.of(2024, 6, 15);
        LocalDate date2 = LocalDate.of(2024, 9, 15);
        long monthsBetween = operations.monthsBetween(date1, date2);
        assertEquals(3, monthsBetween);
    }

    @Test
    @DisplayName("Test years between two dates")
    public void testYearsBetween() {
        LocalDate date1 = LocalDate.of(2024, 6, 15);
        LocalDate date2 = LocalDate.of(2027, 6, 15);
        long yearsBetween = operations.yearsBetween(date1, date2);
        assertEquals(3, yearsBetween);
    }

    @Test
    @DisplayName("Test getting first day of month")
    public void testGetFirstDayOfMonth() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        LocalDate firstDay = operations.getFirstDayOfMonth(date);
        assertEquals(1, firstDay.getDayOfMonth());
        assertEquals(6, firstDay.getMonthValue());
    }

    @Test
    @DisplayName("Test getting last day of month")
    public void testGetLastDayOfMonth() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        LocalDate lastDay = operations.getLastDayOfMonth(date);
        assertEquals(30, lastDay.getDayOfMonth());
        assertEquals(6, lastDay.getMonthValue());
    }

    @Test
    @DisplayName("Test comparing dates")
    public void testCompareDates() {
        LocalDate date1 = LocalDate.of(2024, 6, 15);
        LocalDate date2 = LocalDate.of(2024, 6, 20);
        LocalDate date3 = LocalDate.of(2024, 6, 15);

        assertTrue(operations.compareDates(date1, date2) < 0);
        assertTrue(operations.compareDates(date2, date1) > 0);
        assertEquals(0, operations.compareDates(date1, date3));
    }

    @Test
    @DisplayName("Test checking if date is before")
    public void testIsDateBefore() {
        LocalDate date1 = LocalDate.of(2024, 6, 15);
        LocalDate date2 = LocalDate.of(2024, 6, 20);

        assertTrue(operations.isDateBefore(date1, date2));
        assertFalse(operations.isDateBefore(date2, date1));
    }

    @Test
    @DisplayName("Test checking if date is after")
    public void testIsDateAfter() {
        LocalDate date1 = LocalDate.of(2024, 6, 15);
        LocalDate date2 = LocalDate.of(2024, 6, 20);

        assertTrue(operations.isDateAfter(date2, date1));
        assertFalse(operations.isDateAfter(date1, date2));
    }
}
