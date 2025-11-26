package com.suprun.coding;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NioTasks {

    public static void main(String[] args) {

        NioTasks.writeToFile();
    }

    private static void writeToFile() {
        Path path = Paths.get("output.txt");
        try {
            Files.writeString(path, "I write string to file!");
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }
}
