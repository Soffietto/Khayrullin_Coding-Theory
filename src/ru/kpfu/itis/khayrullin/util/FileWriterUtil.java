package ru.kpfu.itis.khayrullin.util;

import ru.kpfu.itis.khayrullin.entity.HuffmanNode;
import ru.kpfu.itis.khayrullin.helper.EntropyPrinter;
import ru.kpfu.itis.khayrullin.util.EntropyCounterUtil;
import ru.kpfu.itis.khayrullin.util.FileReaderUtil;
import ru.kpfu.itis.khayrullin.util.huffman.HuffmanCodeUtil;
import ru.kpfu.itis.khayrullin.util.huffman.HuffmanQueue;
import ru.kpfu.itis.khayrullin.util.shannonfano.ShannonFanoCodeUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

public class FileWriterUtil {

    public static void generateShannonFanoFile(String fileName, int charCount) throws IOException {
        Map<String, String> codes = ShannonFanoCodeUtil.getCodes(fileName, charCount);
        System.out.println("CODES: ");
        codes.forEach((o1, o2) -> {
            System.out.println(o1 + " : " + o2);
        });
        String text = FileReaderUtil.getTextString(fileName);

        String bitText = getBitText(codes, text, charCount);
        byte[] byteText = new BigInteger(bitText, 2).toByteArray();
        String outputPath = String.format("resources/output/%s_shannon_output_%s.bin",
                fileName.substring(6, fileName.length() - 4).replaceAll("\\s", "_"),
                charCount);
        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
        fileOutputStream.write(byteText);
        EntropyPrinter.entropyPrint(fileName, charCount);
        System.out.println("Bits per symbol: " + (double) (byteText.length * 8) / (double) text.length());
    }

    public static void getHuffmanCode(String fileName, int charCount) throws IOException {
        String text = FileReaderUtil.getTextString(fileName);

        Map<String, Long> stringMap = FileReaderUtil.stringMapCreator(fileName, charCount).getKey();

        HuffmanQueue huffmanQueue = new HuffmanQueue();

        stringMap.forEach((key, value) -> huffmanQueue.insert(new HuffmanNode(key, value)));

        HuffmanNode huffmanTree = HuffmanCodeUtil.buildHuffmanTree(huffmanQueue);
        System.out.println("CODES:");
        Map<String, String> huffmanCodes = HuffmanCodeUtil.getHuffmanCodes(huffmanTree, "");

        String bitText = getBitText(huffmanCodes, text, charCount);

        String outputPath = String.format("resources/output/%s_huffman_output_%s.bin",
                fileName.substring(6, fileName.length() - 4).replaceAll("\\s", "_"),
                charCount);
        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
        byte[] byteText = (new BigInteger(bitText, 2)).toByteArray();
        EntropyPrinter.entropyPrint(fileName, charCount);
        System.out.println("Bits per symbol: " + (double) (byteText.length * 8) / (double) text.length());
        fileOutputStream.write(byteText);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static void generateArithmeticCode(String fileName, int charCount) throws IOException {
        String text = FileReaderUtil.getTextString(fileName);
        String code = ArithmeticCodingUtil.getArithmeticCodes(fileName, charCount);
        String bitText = new BigInteger(code).toString(2);
        String outputPath = String.format("resources/output/%s_arithmetic_output_%s.bin",
                fileName.substring(6, fileName.length() - 4).replaceAll("\\s", "_"),
                charCount);
        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
        byte[] byteText = (new BigInteger(bitText, 2)).toByteArray();
        EntropyPrinter.entropyPrint(fileName, charCount);
        System.out.println("Bits per symbol: " + (double) (byteText.length * 8) / (double) text.length());
        fileOutputStream.write(byteText);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static String getBitText(Map<String, String> codes, String text, int charCount) {
        StringBuilder bitStringBuilder = new StringBuilder();
        for (int i = 0; i < text.length() - charCount; i += charCount) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < charCount; j++) {
                stringBuilder.append(text.charAt(i + j));
            }
            String resultString = stringBuilder.toString();
            String code = codes.get(resultString);
            bitStringBuilder.append(code);
        }
        return bitStringBuilder.toString();
    }
}
