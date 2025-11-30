package com.suprun.coding;

import java.io.*;

public class InputOutputStreamTasks {

    public static void main(String[] args) {
        writeBytesToFile();
        readBytesFromFile();
        readWriteBytesBuffered();
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

    private static void readWriteBytesBuffered(){
        try (
                InputStream in = new BufferedInputStream(new FileInputStream("input_output.txt"));
                OutputStream out = new BufferedOutputStream(new FileOutputStream("input_output.txt"))
        ) {
            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
