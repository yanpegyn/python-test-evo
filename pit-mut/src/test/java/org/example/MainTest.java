package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void mainShouldPrintFiveIterations() {
        // Captura a saída do console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Executa o main
        Main.main(new String[]{});

        // Converte saída para string
        String output = outContent.toString();

        // Verifica que todas as iterações de 1 a 5 foram impressas
        for (int i = 1; i <= 5; i++) {
            assertTrue(output.contains("i = " + i),
                    "Saída deveria conter 'i = " + i + "'");
        }

        // Também valida que NÃO existem impressões além de 5
        assertTrue(!output.contains("i = 6"),
                "Saída não deveria conter 'i = 6'");
    }
}
