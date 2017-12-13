package ru.kpfu.itis.khayrullin.entity;

import java.math.BigDecimal;

public class ArithmeticCodingNode {
    private String value;
    private Double lProbability;
    private Double rProbability;

    public ArithmeticCodingNode(String value, Double lProbability, Double rProbability) {
        this.value = value;
        this.lProbability = lProbability;
        this.rProbability = rProbability;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getlProbability() {
        return lProbability;
    }

    public void setlProbability(Double lProbability) {
        this.lProbability = lProbability;
    }

    public Double getrProbability() {
        return rProbability;
    }

    public void setProbability(Double rProbability) {
        this.rProbability = rProbability;
    }

    @Override
    public String toString() {
        return "ArithmeticCodingNode{" +
                "value='" + value + '\'' +
                ", lProb=" + lProbability +
                ", rProb=" + rProbability +
                '}';
    }
}
