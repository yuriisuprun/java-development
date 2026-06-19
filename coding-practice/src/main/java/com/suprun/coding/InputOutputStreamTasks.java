package com.suprun.coding;

import java.io.*;

/**
 * Utility class for Java I/O Stream operations.
 * Provides methods for reading and writing bytes using InputStream and OutputStream.
 *
 * @author Yurii_Suprun
 */
public class InputOutputStreamTasks {

    /**
     * Writes text to a file using FileOutputStream.
     *
     * @param filename the path to the output file
     * @param text the text to write
     * @throws IOException if an I/O error occurs
     */
    public static void writeBytesToFile(String filename, String text) throws IOException {
        try (OutputStream out = new FileOutputStream(filename)) {
            out.write(text.getBytes());
        }
    }

    /**
     * Reads bytes from a file using FileInputStream.
     *
     * @param filename the path to the input file
     * @return the file contents as a string
     * @throws IOException if an I/O error occurs
     */
    public static String readBytesFromFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (InputStream in = new FileInputStream(filename)) {
            int data;
            while ((data = in.read()) != -1) {
                content.append((char) data);
            }
        }
        return content.toString();
    }

    /**
     * Reads and writes bytes using buffered streams for better performance.
     *
     * @param inputFile the input file path
     * @param outputFile the output file path
     * @throws IOException if an I/O error occurs
     */
    public static void readWriteBytesBuffered(String inputFile, String outputFile) throws IOException {
        try (
                InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
                OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile))
        ) {
            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        }
    }
}
