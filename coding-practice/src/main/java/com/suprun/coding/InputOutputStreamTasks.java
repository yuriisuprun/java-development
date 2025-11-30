package com.suprun.coding;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class InputOutputStreamTasks {

    public static void main(String[] args) {
        writeBytesToFile();
    }

    private static void writeBytesToFile() {
        String text = "Hello OutputStream!";
        try (OutputStream out = new FileOutputStream("input_output.txt")) {
            out.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
