package ru.kpfu.itis.khayrullin.util.huffman;

import ru.kpfu.itis.khayrullin.entity.HuffmanNode;

import java.util.HashMap;
import java.util.Map;

public class HuffmanCodeUtil {

//    private static final String FILE_NAME = "input/bulgakov sobache serdce.txt";

    private static HashMap<String, String> huffMap = new HashMap<>();

    private static void makeHashMap(HuffmanNode node, String path) {
        //flag = 1 for left and flag = 2 for right
        if (node.isLeaf()) {
            System.out.println(node.getValue() + " : " + path);
            huffMap.put(node.getValue(), path);
        } else if (!node.isLeaf())    //n != null
        {
            makeHashMap(node.getLeft(), path + "0");
            makeHashMap(node.getRight(), path + "1");
        }
    }

    public static HuffmanNode buildHuffmanTree(HuffmanQueue q) {
        HuffmanQueue myMinQueue = q.clone();
        HuffmanNode min1, min2, temp = new HuffmanNode("\0");
        while (myMinQueue.getCount() > 1) {
            min1 = myMinQueue.extractMin();
            min2 = myMinQueue.extractMin();
            temp = new HuffmanNode("#", min1.getFrequency() + min2.getFrequency());
            myMinQueue.insert(temp);
            temp.setLeft(min1);
            temp.setRight(min2);
        }
        return temp;
    }

    public static Map<String, String> getHuffmanCodes(HuffmanNode node, String path) {
        makeHashMap(node, path);
        return huffMap;
    }

}
