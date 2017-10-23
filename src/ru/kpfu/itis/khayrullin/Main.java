package ru.kpfu.itis.khayrullin;

import ru.kpfu.itis.khayrullin.helper.EntropyPrinter;
import ru.kpfu.itis.khayrullin.util.TextWriterUtil;

import java.io.IOException;

public class Main {

    private static final String FILE_NAME = "input/bulgakov sobache serdce.txt";
    private static final String NEW_FILE_NAME = "new file.txt";

    public static void main(String[] args) throws IOException {
        EntropyPrinter.entropyPrint(FILE_NAME, 1);
        EntropyPrinter.entropyPrint(FILE_NAME, 2);
        EntropyPrinter.entropyPrint(FILE_NAME, 3);
        EntropyPrinter.entropyPrint(FILE_NAME, 4);
        EntropyPrinter.entropyPrint(FILE_NAME, 5);
        EntropyPrinter.entropyPrint(FILE_NAME, 6);
        TextWriterUtil.writeNewText(FILE_NAME, NEW_FILE_NAME,1000, 10);
    }
}
