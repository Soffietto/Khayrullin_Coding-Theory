package ru.kpfu.itis.khayrullin.util;

import ru.kpfu.itis.khayrullin.helper.Pair;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class FileReaderUtil {

    private static Pair<Map<String, Long>, Long> stringMapCreator(String fileName, Integer charCount) throws IOException {
        Map<String, Long> stringLongMap = new HashMap<>();
        Reader reader = new InputStreamReader(FileReaderUtil.class.getClassLoader().getResourceAsStream(fileName));
        Long sum = 0L;
        int readedInt = 0;
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < charCount; i++) {
            readedInt = reader.read();
            resultString.append((char) readedInt);
        }
        while (readedInt != -1) {
            String iString = resultString.toString();

            Long currentCharVal = stringLongMap.get(iString);
            if (currentCharVal == null) {
                stringLongMap.put(iString, 1L);
            } else {
                stringLongMap.put(iString, ++currentCharVal);
            }
            resultString = new StringBuilder();
            if (iString.length() != 1) {
                resultString.append(iString.substring(1));
            }
            readedInt = reader.read();
            resultString.append((char) readedInt);
            sum++;
        }
        return new Pair<>(stringLongMap, sum);
    }

    public static Pair<Map<String, Double>, Long> getStringProbabilities(String fileName, Integer charCount) throws IOException {
        Pair<Map<String, Long>, Long> pair = stringMapCreator(fileName, charCount);
        Map<String, Long> stringLongMap = pair.getKey();
        Long sumOfStrings = pair.getValue();
        Map<String, Double> probabilities = new HashMap<>();
        stringLongMap.forEach((string, aLong) -> {
            probabilities.put(string, aLong / (double) sumOfStrings);
        });
        return new Pair<>(probabilities, sumOfStrings);
    }

}
