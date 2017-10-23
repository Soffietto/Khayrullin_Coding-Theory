package ru.kpfu.itis.khayrullin.helper;

import ru.kpfu.itis.khayrullin.entity.Entropy;
import ru.kpfu.itis.khayrullin.util.EntropyCounterUtil;

import java.io.IOException;

public class EntropyPrinter {

    public static void entropyPrint(String fileName, int conditionPower) throws IOException {
        Entropy entropy = EntropyCounterUtil.conditionEntropyCounter(fileName, conditionPower);
        Double doubleEntropy = entropy.getEntropy();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("H(");
        for (int i = 0; i < conditionPower; i++) {
            stringBuilder.append(String.format("U%s", i + 1));
            if (i != conditionPower - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(String.format(") = %s", doubleEntropy));
        System.out.println(stringBuilder.toString());
    }
}
