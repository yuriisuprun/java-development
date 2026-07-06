package com.suprun.lambdas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for Main class.
 *
 * <p>Verifies that the main method executes correctly and produces expected output.
 *
 * @author Yurii_Suprun
 * @version 1.0
 */
class MainTest {

    @Test
    @DisplayName("Main method should execute without errors")
    void testMainExecution() {
        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Main.main(new String[]{});

            String output = outContent.toString();
            System.setOut(originalOut);

            // Verify expected output
            assertTrue(output.contains("8"), "Output should contain '8' from method reference");
            assertTrue(output.contains("64"), "Output should contain '64' from composed function");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("Main method with empty args should work correctly")
    void testMainWithEmptyArgs() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Main.main(new String[]{});
            String output = outContent.toString();
            System.setOut(originalOut);
            assertTrue(!output.isEmpty(), "Main should produce output");
        } finally {
            System.setOut(originalOut);
        }
    }
}
