package ru.kpfu.itis.khayrullin.entity;

public class HuffmanNode {
    private String value;
    private Long frequency;
    private HuffmanNode left, right;
    private String path ="";

    public HuffmanNode(String key) {
        value = key;
        frequency = 0L;
        left = null;
        right = null;
    }

    public HuffmanNode(String key, Long freq) {
        value = key;
        frequency = freq;
        left = null;
        right = null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", frequency=" + frequency +
                ", path='" + path + '\'' +
                '}';
    }

    public boolean isLeaf() {
        return ((left == null) && (right == null));
    }
}