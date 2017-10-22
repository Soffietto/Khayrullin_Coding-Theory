package ru.kpfu.itis.khayrullin;

import ru.kpfu.itis.khayrullin.helper.EntropyPrinter;

import java.io.IOException;

public class Main {

    private static final String FILE_NAME = "bulgakov sobache serdce.txt";

    public static void main(String[] args) throws IOException {
        EntropyPrinter.entropyPrint(FILE_NAME, 1);
        EntropyPrinter.entropyPrint(FILE_NAME, 2);
        EntropyPrinter.entropyPrint(FILE_NAME, 3);
        EntropyPrinter.entropyPrint(FILE_NAME, 4);
        EntropyPrinter.entropyPrint(FILE_NAME, 5);
        EntropyPrinter.entropyPrint(FILE_NAME, 6);
    }
}
