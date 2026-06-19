package com.suprun.coding;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility class for Java NIO file operations.
 * Provides methods for reading and writing files using the Files API.
 *
 * @author Yurii_Suprun
 */
public class NioTasks {

    /**
     * Writes a string to a file using NIO Files API.
     *
     * @param path the file path
     * @param content the content to write
     * @throws IOException if an I/O error occurs
     */
    public static void writeToFile(Path path, String content) throws IOException {
        Files.writeString(path, content);
    }

    /**
     * Reads all lines from a file using NIO Files API.
     *
     * @param path the file path
     * @return list of lines from the file
     * @throws IOException if an I/O error occurs
     */
    public static List<String> readToFile(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    /**
     * Reads the entire file as a single string.
     *
     * @param path the file path
     * @return file contents as string
     * @throws IOException if an I/O error occurs
     */
    public static String readFileAsString(Path path) throws IOException {
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}
