package ru.kpfu.itis.khayrullin;

import ru.kpfu.itis.khayrullin.util.FileWriterUtil;

import java.io.IOException;

public class Main {

    private static final String FILE_NAME = "input/bulgakov sobache serdce.txt";
    private static final String NEW_FILE_NAME = "new file.txt";

    public static void main(String[] args) throws IOException {
//        EntropyPrinter.entropyPrint(FILE_NAME, 1);
//        EntropyPrinter.entropyPrint(FILE_NAME, 2);
//        EntropyPrinter.entropyPrint(FILE_NAME, 3);

//        TextWriterUtil.writeNewText(FILE_NAME, NEW_FILE_NAME,1000, 7);

        FileWriterUtil.getHuffmanCode(FILE_NAME, 2);
//        FileWriterUtil.getHuffmanCode(FILE_NAME, 2);

//        FileWriterUtil.generateShannonFanoFile(FILE_NAME, 1);
        FileWriterUtil.generateShannonFanoFile(FILE_NAME, 2);

    }
}
