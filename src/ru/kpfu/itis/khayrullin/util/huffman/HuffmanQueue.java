package ru.kpfu.itis.khayrullin.util.huffman;

import ru.kpfu.itis.khayrullin.entity.HuffmanNode;

public class HuffmanQueue implements Cloneable {
    private HuffmanNode nodes[];
    private int count;

    public HuffmanQueue() {
        nodes = new HuffmanNode[20];
        count = 0;
    }

    @Override
    public HuffmanQueue clone() {
        final HuffmanQueue clone;
        try {
            clone = (HuffmanQueue) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException("superclass messed up", ex);
        }
        return clone;
    }

    public int getCount() {
        return count;
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int min;
        if ((l <= count) && (nodes[l].getFrequency() < nodes[i].getFrequency()))
            min = l;
        else
            min = i;
        if ((r <= count) && (nodes[r].getFrequency() < nodes[min].getFrequency()))
            min = r;
        if (min != i) {
            HuffmanNode temp = nodes[i];
            nodes[i] = nodes[min];
            nodes[min] = temp;
            minHeapify(min);
        }
    }


    public void insert(HuffmanNode node) {
        count++;
        checkCount();
        nodes[count] = node;
        int i = count;
        HuffmanNode temp;

        while (i > 1 && (nodes[parent(i)].getFrequency() > nodes[i].getFrequency()))    //Если родительское меньше чем потомка
        {
            temp = nodes[i];
            nodes[i] = nodes[parent(i)];
            nodes[parent(i)] = temp;
            i = parent(i);
        }
    }

    public HuffmanNode extractMin() {
        if (count < 1) {
            System.out.println("Heap is empty");
            return null;
        }

        HuffmanNode min = nodes[1];
        nodes[1] = nodes[count--];
        minHeapify(1);
        return min;
    }


    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private int parent(int i) {
        if (i > 1)
            return i / 2;
        else
            return 0;
    }

    private void incSize() {
        HuffmanNode[] temp = new HuffmanNode[2 * nodes.length];
        System.arraycopy(nodes, 0, temp, 0, nodes.length);
        nodes = temp;
    }

    private void decSize() {
        HuffmanNode[] temp = new HuffmanNode[(nodes.length) / 2];
        System.arraycopy(nodes, 0, temp, 0, temp.length);
        nodes = temp;
    }

    private void checkCount() {
        if (count >= nodes.length)
            incSize();
        else if ((count > 10) && (count < nodes.length / 4))
            decSize();
    }

}