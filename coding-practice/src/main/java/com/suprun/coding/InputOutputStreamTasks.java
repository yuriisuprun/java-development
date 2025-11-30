package com.suprun.coding;

import java.io.*;

public class InputOutputStreamTasks {

    public static void main(String[] args) {
        writeBytesToFile();
        readBytesFromFile();
    }

    private static void writeBytesToFile() {
        String text = "Hello Input Output Stream!";
        try (OutputStream out = new FileOutputStream("input_output.txt")) {
            out.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readBytesFromFile() {
        try (InputStream in = new FileInputStream("input_output.txt")) {
            int data;
            while ((data = in.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
