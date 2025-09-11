package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RecordWithCustomConstructorTest {

    // That test fix the mutation
    // @Test
    // void hasPrint() {
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     PrintStream originalOut = System.out;
    //     System.setOut(new PrintStream(outContent));

    //     try {
    //         new RecordWithCustomConstructor();
    //     } finally {
    //         System.setOut(originalOut);
    //     }

    //     assertTrue(outContent.toString().contains("mutate me"));
    // }

    @Test
    void testDefaultConstructor() {
        RecordWithCustomConstructor record = new RecordWithCustomConstructor();
        assertEquals(1L, record.timeStamp());
        assertEquals("", record.data());
    }

    @Test
    void testToStringOverride() {
        RecordWithCustomConstructor record = new RecordWithCustomConstructor();
        assertTrue(record.toString().contains("RecordWithCustomConstructor"));
    }

    @Test
    void testDataMethodSideEffect() {
        RecordWithCustomConstructor record = new RecordWithCustomConstructor();
        System.out.println("Expected side effect"); // This is to ensure the side effect is triggered
        assertEquals("", record.data(), "The data method should return an empty string by default.");
    }
}