package com.suprun.coding;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link NioTasks}
 *
 * @author Yurii_Suprun
 */
@DisplayName("NIO Tasks Tests")
public class NioTasksTest {

    private static final String TEST_FILE = "test_nio_file.txt";
    private Path testPath;

    @BeforeEach
    void setUp() {
        testPath = Paths.get(TEST_FILE);
        // Clean up any existing test file
        try {
            Files.deleteIfExists(testPath);
        } catch (IOException e) {
            // File doesn't exist, which is fine
        }
    }

    @AfterEach
    void tearDown() {
        // Clean up test file after each test
        try {
            Files.deleteIfExists(testPath);
        } catch (IOException e) {
            // File doesn't exist or can't be deleted, which is fine
        }
    }

    @Test
    @DisplayName("Should write string to file")
    void testWriteToFile() throws IOException {
        String content = "Write once, run anywhere!";
        NioTasks.writeToFile(testPath, content);

        assertTrue(Files.exists(testPath));
        String readContent = Files.readString(testPath);
        assertEquals(content, readContent);
    }

    @Test
    @DisplayName("Should read lines from file")
    void testReadToFile() throws IOException {
        String content = "Line 1\nLine 2\nLine 3";
        Files.writeString(testPath, content);

        List<String> lines = NioTasks.readToFile(testPath);
        assertEquals(3, lines.size());
        assertEquals("Line 1", lines.get(0));
        assertEquals("Line 2", lines.get(1));
        assertEquals("Line 3", lines.get(2));
    }

    @Test
    @DisplayName("Should read entire file as string")
    void testReadFileAsString() throws IOException {
        String content = "Hello\nWorld\nNIO";
        Files.writeString(testPath, content);

        String result = NioTasks.readFileAsString(testPath);
        assertEquals(content, result);
    }

    @Test
    @DisplayName("Should handle single line file")
    void testReadSingleLineFile() throws IOException {
        String content = "Single line";
        NioTasks.writeToFile(testPath, content);

        List<String> lines = NioTasks.readToFile(testPath);
        assertEquals(1, lines.size());
        assertEquals(content, lines.get(0));
    }

    @Test
    @DisplayName("Should handle empty file")
    void testEmptyFile() throws IOException {
        NioTasks.writeToFile(testPath, "");

        List<String> lines = NioTasks.readToFile(testPath);
        assertTrue(lines.isEmpty());

        String content = NioTasks.readFileAsString(testPath);
        assertEquals("", content);
    }

    @Test
    @DisplayName("Should overwrite existing file")
    void testOverwriteFile() throws IOException {
        NioTasks.writeToFile(testPath, "Original content");
        NioTasks.writeToFile(testPath, "New content");

        String result = NioTasks.readFileAsString(testPath);
        assertEquals("New content", result);
    }

    @Test
    @DisplayName("Should handle multiline with special characters")
    void testMultilineWithSpecialCharacters() throws IOException {
        String content = "Hello @#$%\nWorld &*()\nNIO\\n";
        NioTasks.writeToFile(testPath, content);

        String result = NioTasks.readFileAsString(testPath);
        assertEquals(content, result);
    }
}
