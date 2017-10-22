package ru.kpfu.itis.khayrullin.util;

import ru.kpfu.itis.khayrullin.entity.Entropy;
import ru.kpfu.itis.khayrullin.helper.Pair;

import java.io.IOException;
import java.util.Map;

public class EntropyCounterUtil {

    public static Entropy conditionEntropyCounter(String fileName, int conditionPower) throws IOException {
        if(conditionPower == 1) {
            Map<String, Double> oneCharProbability = FileReaderUtil.getStringProbabilities(fileName, 1).getKey();
            Long charCounter = (long) oneCharProbability.keySet().size();
            return new Entropy(textEntropyCounter(fileName), oneCharProbability, charCounter);
        }
        Pair<Map<String, Double>, Long> firstCharPair = FileReaderUtil.getStringProbabilities(fileName, conditionPower - 1);
        Pair<Map<String, Double>, Long> secondCharPair = FileReaderUtil.getStringProbabilities(fileName, conditionPower);
        Map<String, Double> twoCharProbabilities = firstCharPair.getKey();
        Map<String, Double> threeCharProbabilities = secondCharPair.getKey();
        Double entropy = entrupyCounter(twoCharProbabilities, threeCharProbabilities);
        return new Entropy((-1) * entropy, threeCharProbabilities, firstCharPair.getValue() + conditionPower - 2);
    }

    public static Double fullEntropyCounter(Entropy entropy) {
        Double entropyDouble = entropy.getEntropy();
        Long charSum = entropy.getCharSum();
        return entropyDouble * charSum;
    }

    private static Double textEntropyCounter(String fileName) throws IOException {
        Pair<Map<String, Double>, Long> oneCharPair = FileReaderUtil.getStringProbabilities(fileName, 1);
        Map<String, Double> oneCharProbabilities = oneCharPair.getKey();
        return (-1) * oneCharProbabilities.entrySet()
                .stream()
                .mapToDouble((o1) -> {
                    Double value = o1.getValue();
                    return (value * Math.log(value) / Math.log(2.0));
                })
                .sum();
    }

    private static Double entrupyCounter(Map<String, Double> firstCharProbabilities, Map<String, Double> secondCharProbabilities) {
        return secondCharProbabilities.entrySet()
                .stream()
                .mapToDouble((o1) -> {
                    Double value = o1.getValue();
                    String beginning = o1.getKey().substring(1);
                    Double charValue = firstCharProbabilities.get(beginning);
                    return value * Math.log(value / charValue) / Math.log(2.0);
                })
                .sum();
    }
}
