package ru.kpfu.itis.khayrullin.helper;

import java.util.ArrayList;
import java.util.List;

public class TextSplitter {

    public static List<String> stringSplits(String text, int charCount) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < text.length() - charCount + 1; i++) {
            String string = text.substring(i, i + charCount);
            strings.add(string);
        }
        return strings;
    }
}
