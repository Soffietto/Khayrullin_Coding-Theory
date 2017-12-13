package tolya;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class ArithmeticCoding {
    static StringBuilder result = new StringBuilder();
    static int countZeroOnBegin = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/input/file.txt")));
        Map<String, Double> ver = new HashMap<>();
        StringBuilder text = new StringBuilder();
        Map<String, Segment> segments = new HashMap<>();
//        Map<String, Segment> segmentsSlog = new HashMap<>();
        int s;
        while ((s = br.read()) != -1) {
            text.append((char) s);
            String symbol = String.valueOf((char) s);
            if (ver.get(symbol) != null) {
                double count = ver.get(symbol);
                ver.put(symbol, count + 1.0);
            } else {
                ver.put(symbol, 1.0);
            }
        }
        double border = 0;
        for (Map.Entry entry : ver.entrySet()) {
            System.out.println(entry.getValue());
            segments.put((String) entry.getKey(), new Segment((String) entry.getKey(), border,
                    border + (Double) entry.getValue() / text.length()));
            border = border + (Double) entry.getValue() / text.length();
        }
        for (Map.Entry segment : segments.entrySet()) {
            System.out.println(segment.getKey() + " " + ((Segment) segment.getValue()).getLeftBorder() + " " + ((Segment) segment.getValue()).getRightBorder());
        }
        arithmeticCoding(segments, text);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void arithmeticCoding(Map<String, Segment> segments, StringBuilder text) {
        char[] letters = text.toString().toCharArray();
        double left = 0;
        double right = 1;
        for (int i = 0; i < letters.length; i++) {
            Segment segment = segments.get(String.valueOf(letters[i]));
            double newRight = left + (right - left) * segment.getRightBorder();
            double newLeft = left + (right - left) * segment.getLeftBorder();
            Segment checkSegment = checkOverlap(newLeft, newRight);
            left = checkSegment.getLeftBorder();
            right = checkSegment.getRightBorder();
        }

        result.append((String.valueOf((left + right) / 2).substring(2)));
        System.out.println("Binary Write " + new BigInteger(result.toString()).toString(2));
        System.out.println("ALL " + result);
    }

    public static Segment checkOverlap(double left, double right) {
        double newLeft = left * 10;
        double newRight = right * 10;
        while ((int) newLeft == (int) newRight) {
            result.append(String.valueOf((int) newLeft));
//            left = new BigDecimal(newLeft - (int) newLeft).doubleValue();
            left = new BigDecimal(newLeft - (int) newLeft).setScale(16, BigDecimal.ROUND_CEILING).doubleValue();
//            right = new BigDecimal(newRight - (int) newRight).doubleValue();
            right = new BigDecimal(newRight - (int) newRight).setScale(16, BigDecimal.ROUND_FLOOR).doubleValue();
//            right = newRight - (int) newRight;
            newLeft = left * 10;
            newRight = right * 10;
        }
        return new Segment(" ", left, right);
    }
}