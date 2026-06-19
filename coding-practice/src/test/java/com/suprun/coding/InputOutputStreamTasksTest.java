package com.suprun.coding;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link InputOutputStreamTasks}
 *
 * @author Yurii_Suprun
 */
@DisplayName("Input/Output Stream Tasks Tests")
public class InputOutputStreamTasksTest {

    private static final String TEST_FILE = "test_io_operations.txt";
    private static final String TEST_OUTPUT_FILE = "test_io_output.txt";

    @BeforeEach
    void setUp() {
        // Clean up any existing test files
        new File(TEST_FILE).delete();
        new File(TEST_OUTPUT_FILE).delete();
    }

    @AfterEach
    void tearDown() {
        // Clean up test files after each test
        new File(TEST_FILE).delete();
        new File(TEST_OUTPUT_FILE).delete();
    }

    @Test
    @DisplayName("Should write bytes to file")
    void testWriteBytesToFile() throws IOException {
        String text = "Hello Input Output Stream!";
        InputOutputStreamTasks.writeBytesToFile(TEST_FILE, text);

        assertTrue(new File(TEST_FILE).exists());
        String content = new String(Files.readAllBytes(Paths.get(TEST_FILE)));
        assertEquals(text, content);
    }

    @Test
    @DisplayName("Should read bytes from file")
    void testReadBytesFromFile() throws IOException {
        String text = "Test content for reading";
        InputOutputStreamTasks.writeBytesToFile(TEST_FILE, text);

        String result = InputOutputStreamTasks.readBytesFromFile(TEST_FILE);
        assertEquals(text, result);
    }

    @Test
    @DisplayName("Should handle empty file reading")
    void testReadBytesFromEmptyFile() throws IOException {
        InputOutputStreamTasks.writeBytesToFile(TEST_FILE, "");
        String result = InputOutputStreamTasks.readBytesFromFile(TEST_FILE);
        assertEquals("", result);
    }

    @Test
    @DisplayName("Should copy file using buffered streams")
    void testReadWriteBytesBuffered() throws IOException {
        String text = "Test buffered read/write operations";
        InputOutputStreamTasks.writeBytesToFile(TEST_FILE, text);

        InputOutputStreamTasks.readWriteBytesBuffered(TEST_FILE, TEST_OUTPUT_FILE);

        assertTrue(new File(TEST_OUTPUT_FILE).exists());
        String content = new String(Files.readAllBytes(Paths.get(TEST_OUTPUT_FILE)));
        assertEquals(text, content);
    }

    @Test
    @DisplayName("Should handle multiline content")
    void testWriteAndReadMultilineContent() throws IOException {
        String text = "Line 1\nLine 2\nLine 3";
        InputOutputStreamTasks.writeBytesToFile(TEST_FILE, text);

        String result = InputOutputStreamTasks.readBytesFromFile(TEST_FILE);
        assertEquals(text, result);
    }
}
