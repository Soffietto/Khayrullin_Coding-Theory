package ru.kpfu.itis.khayrullin.util;

import ru.kpfu.itis.khayrullin.entity.Entropy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

public class TextWriterUtil {

    public static void writeNewText(String oldFileName, String newFileName, long textLength, int conditionCounter) throws IOException {
        Entropy entropy = EntropyCounterUtil.conditionEntropyCounter(oldFileName, conditionCounter);
        Map<String, Double> charProbabilities = entropy.getCharacterProbabilities()
                .entrySet()
                .stream()
                .sorted((o1, o2) -> (-1) * o1.getValue().compareTo(o2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Writer writer = new FileWriter(new File("resources/output/" + newFileName));
        Set<Map.Entry<String, Double>> entry = charProbabilities.entrySet();
        Map.Entry[] entries = new Map.Entry[entry.size()];
        entry.toArray(entries);
        String substring = entries[(int) (Math.random() * entries.length)].getKey().toString();
        writer.write(substring);
        for (int i = 0; i < textLength; i++) {
            substring = substring.substring(1);
            for (Map.Entry entryI : entries) {
                String key = entryI.getKey().toString();
                if (key.startsWith(substring)) {
                    substring = key;
                    writer.write(key.charAt(key.length() - 1));
                    break;
                }
            }
        }
        writer.close();
        System.out.println("Текст успешно сгенерирован:");
        System.out.println(String.format("Файл-основа - %s", oldFileName));
        System.out.println(String.format("Название файла - %s", newFileName));
        System.out.println(String.format("Энтропия от %s символов", conditionCounter));
    }
}
