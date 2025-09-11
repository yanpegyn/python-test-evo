package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CustomRecordTest {

    @Test
    void testToString() {
        CustomRecord record = new CustomRecord(System.currentTimeMillis(), "testData");
        assertEquals("overridden", record.toString());
    }

    @Test
    void dataMethodSideEffect() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CustomRecord record = new CustomRecord(System.currentTimeMillis(), "testData");
        assertEquals("testData", record.data());
        assertTrue(outContent.toString().contains("side effect"));
    }

    @Test
    void extraMethodReturns42() {
        CustomRecord record = new CustomRecord(System.currentTimeMillis(), "testData");
        assertEquals(42, record.extraMethod());
    }
}