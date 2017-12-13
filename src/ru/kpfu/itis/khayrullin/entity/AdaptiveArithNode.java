package ru.kpfu.itis.khayrullin.entity;

public class AdaptiveArithNode {

    private String symbol;
    private Double probability;
    private Double lProbability;
    private Double rProbability;

    public AdaptiveArithNode(String symbol) {
        this.symbol = symbol;
    }

    public AdaptiveArithNode(String symbol, Double probability, Double lProbability, Double rProbability) {
        this.symbol = symbol;
        this.probability = probability;
        this.lProbability = lProbability;
        this.rProbability = rProbability;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
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

    public void setrProbability(Double rProbability) {
        this.rProbability = rProbability;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof AdaptiveArithNode && getSymbol().equals(((AdaptiveArithNode) o).getSymbol());
    }
}
