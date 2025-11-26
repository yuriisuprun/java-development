package com.suprun.coding;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class NioTasks {

    public static void main(String[] args) {

        NioTasks.writeToFile();
        NioTasks.readToFile();
    }

    private static void writeToFile() {
        Path path = Paths.get("test.txt");
        try {
            Files.writeString(path, "Write once, run anywhere!");
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

    private static void readToFile() {
        Path path = Paths.get("test.txt");
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Failed to read from file: " + e.getMessage());
        }
    }
}
