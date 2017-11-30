package ru.kpfu.itis.khayrullin.util.shannonfano;

import ru.kpfu.itis.khayrullin.entity.ShannonFanoNode;
import ru.kpfu.itis.khayrullin.util.FileReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShannonFanoCodeUtil {

    public static List<ShannonFanoNode> mapToListConverter(String fileName, Integer charCount) throws IOException {
        Map<String, Long> symbolMap = FileReaderUtil.stringMapCreator(fileName, charCount).getKey();
        List<ShannonFanoNode> shannonFanoNodes = new ArrayList<>();
        symbolMap.forEach((s, aLong) -> shannonFanoNodes.add(new ShannonFanoNode(s, aLong)));
        return shannonFanoNodes;
    }

    public static Map<String, String> getCodes(String fileName, Integer charCount) throws IOException {
        List<ShannonFanoNode> shannonFanoNodes = mapToListConverter(fileName, charCount);
        shannonFanoNodes.sort(Comparator.comparing(ShannonFanoNode::getCount));
        splitter(shannonFanoNodes);
        Map<String, String> codes = shannonFanoNodes.stream()
                .collect(Collectors.toMap(ShannonFanoNode::getSymbol, ShannonFanoNode::getCode));
        return codes;
    }

    private static void splitter(List<ShannonFanoNode> shannonFanoNodes) {
        int size = shannonFanoNodes.size();
        if (size != 1) {

            Long sum = shannonFanoNodes.stream().mapToLong(ShannonFanoNode::getCount).sum();

            List<ShannonFanoNode> shannonFanoNodes1 = new ArrayList<>();
            List<ShannonFanoNode> shannonFanoNodes2 = new ArrayList<>();

            Long weight = 0L;
            for (ShannonFanoNode i : shannonFanoNodes) {
                if (weight + i.getCount() <= sum / 2) {
                    shannonFanoNodes1.add(i);
                    weight += i.getCount();
                } else {
                    shannonFanoNodes2.add(i);
                }
            }

            shannonFanoNodes1.forEach(shannonFanoNode -> {
                String code = shannonFanoNode.getCode();
                shannonFanoNode.setCode(code + 1);
            });
            shannonFanoNodes2.forEach(shannonFanoNode -> {
                String code = shannonFanoNode.getCode();
                shannonFanoNode.setCode(code + 0);
            });
            splitter(shannonFanoNodes1);
            splitter(shannonFanoNodes2);
        }
    }
}
