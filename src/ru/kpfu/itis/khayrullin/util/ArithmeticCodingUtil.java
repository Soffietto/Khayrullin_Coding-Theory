package ru.kpfu.itis.khayrullin.util;

import ru.kpfu.itis.khayrullin.entity.AdaptiveArithNode;
import ru.kpfu.itis.khayrullin.entity.ArithmeticCodingNode;
import ru.kpfu.itis.khayrullin.helper.TextSplitter;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class ArithmeticCodingUtil {

    private static BigDecimal currentLow;
    private static BigDecimal currentHigh;

    private static String text = "";

    public static String getArithmeticCodes(String fileName, int charCount) throws IOException {
        currentLow = new BigDecimal(0);
        currentHigh = new BigDecimal(1);
        int counter = 0;
        Map<String, Double> stringProbabilities = FileReaderUtil.getStringProbabilities(fileName, charCount).getKey();
        String text = FileReaderUtil.getTextString(fileName);
        List<String> splittedText = TextSplitter.stringSplits(text, charCount);
        List<ArithmeticCodingNode> codingNodes = mapSplitter(stringProbabilities);
        for (String currentString : splittedText) {
            ArithmeticCodingNode currentNode = getNodeBySymbol(currentString, codingNodes);
            if (currentNode != null) {
                Double lProbability = currentNode.getlProbability();
                Double rProbability = currentNode.getrProbability();
                BigDecimal newHigh = currentLow.add(currentHigh.subtract(currentLow).multiply(BigDecimal.valueOf(rProbability)));
                BigDecimal newLow = currentLow.add(currentHigh.subtract(currentLow).multiply(BigDecimal.valueOf(lProbability)));
                currentLow = newLow;
                currentHigh = newHigh;
                if (counter % 20000 == 0) {
                    System.out.println("Counter: " + counter);
                }
            }
            counter++;
        }
        return getResult().substring(2);
    }

    public static String getAdaptiveArithmetic(String fileName) throws IOException {
        Set<String> stringSet = FileReaderUtil.getStringProbabilities(fileName, 1)
                .getKey().keySet();
        Map<String, Integer> charCount = new HashMap<>();
        for (String string : stringSet) {
            charCount.put(string, 1);
        }
        text = FileReaderUtil.getTextString(fileName);
        return stepIntoAdapt(0, charCount, charCount.size(), 0.0, 1.0);
    }

    private static String stepIntoAdapt(Integer k, Map<String, Integer> sym, Integer symNum, Double start, Double end) {
        try{
            String currSymbol = (text.substring(k, k + 1));
            Set<String> keySey = sym.keySet();
            ArrayList<String> symList = new ArrayList<>(keySey);
            HashMap<String, Double> symWithVer = new HashMap<>();
            for (int i = 0; i < symList.size(); i++) {
                if (symList.get(i).length() == 1) {
                    String currSym = symList.get(i);
                    symWithVer.put(symList.get(i), sym.get(currSym) / (double)symNum);
                }
            }
            ArrayList<AdaptiveArithNode> newSymList = new ArrayList<>();
            Double oneSymPoint = 0.0;
            for (String symbol : symList) {
                newSymList.add(new AdaptiveArithNode(symbol, symWithVer.get(symbol), oneSymPoint, oneSymPoint + (symWithVer.get(symbol))));
                oneSymPoint = oneSymPoint + symWithVer.get(symbol);
            }
            AdaptiveArithNode currSym = newSymList.get(newSymList.indexOf(new AdaptiveArithNode(currSymbol)));
            String result = "";
            Double newStart = start + (end - start) * currSym.getlProbability();
            Double newEnd = start + (end - start) * currSym.getrProbability();
            String newStartString = String.valueOf(newStart);
            String newEndString = String.valueOf(newEnd);
            int i = 2;
            while (newStartString.length() > i && newStartString.substring(0, i).equals(newEndString.substring(0, i))) {
                i++;
            }
            if (newStartString.length() > i + 2 && !newStartString.substring(i - 1, i + 2).equals("000")) {
                newStart = Double.valueOf("0." + newStartString.substring(i - 1));
                newEnd = Double.valueOf("0." + newEndString.substring(i - 1));
                result = result + newEndString.substring(2, i - 1);
            }
            symNum++;
            sym.replace(currSymbol, sym.get(currSymbol) + 1);
            if (k != text.length() - 2) {
                return result + stepIntoAdapt(k + 1, sym, symNum, newStart, newEnd);
            } else {
                return result + newEndString.charAt(i - 1);
            }
        }catch (StackOverflowError er) {
            System.out.println(k);
            return null;
        }
    }

    private static List<ArithmeticCodingNode> mapSplitter(Map<String, Double> probabilities) {
        List<Map.Entry<String, Double>> sortedMap = probabilities.entrySet().stream()
                .sorted((o1, o2) -> {
                    if (o1.getValue().compareTo(o2.getValue()) == 0) {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                    return (-1) * o1.getValue().compareTo(o2.getValue());
                })
                .collect(Collectors.toList());
        List<ArithmeticCodingNode> arithmeticCodingNodes = new ArrayList<>();
        Map.Entry<String, Double> firstEntry = sortedMap.get(0);
        ArithmeticCodingNode prevNode = new ArithmeticCodingNode(firstEntry.getKey(), 0d, firstEntry.getValue());
        arithmeticCodingNodes.add(prevNode);
        for (int i = 1; i < sortedMap.size(); i++) {
            Map.Entry<String, Double> entry = sortedMap.get(i);
            ArithmeticCodingNode newNode = new ArithmeticCodingNode(entry.getKey(), prevNode.getrProbability(), entry.getValue() + prevNode.getrProbability());
            arithmeticCodingNodes.add(newNode);
            prevNode = newNode;
        }
        arithmeticCodingNodes.get(arithmeticCodingNodes.size() - 1).setProbability(1d);
        return arithmeticCodingNodes;
    }

    private static ArithmeticCodingNode getNodeBySymbol(String symbol, List<ArithmeticCodingNode> codingNodes) {
        if (symbol.equals(" ")) return codingNodes.get(0);
        for (ArithmeticCodingNode node : codingNodes) {
            if (node.getValue().equals(symbol)) return node;
        }
        return null;
    }

    private static BigDecimal stringToBigDecimal(String text) {
        BigDecimal bigDecimal = null;
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            String pattern = "#,##0.0#";
            DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
            decimalFormat.setParseBigDecimal(true);
            bigDecimal = (BigDecimal) decimalFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bigDecimal;
    }

    public static String getResult() {
        String low = currentLow.toString();
        String high = currentHigh.toString();
        for (int i = 0; i < high.length(); i++) {
            if (high.charAt(i) != low.charAt(i)) {
                return high.substring(0, i + 1);
            }
        }
        return high;
    }

}
