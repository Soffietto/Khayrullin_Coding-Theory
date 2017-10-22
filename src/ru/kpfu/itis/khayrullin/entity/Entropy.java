package ru.kpfu.itis.khayrullin.entity;

import java.util.Map;

public class Entropy {
    private Double entropy;
    private Map<String, Double> characterProbabilities;
    private Long charSum;

    public Entropy(Double entropy, Map<String, Double> characterProbabilities, Long charSum) {
        this.entropy = entropy;
        this.characterProbabilities = characterProbabilities;
        this.charSum = charSum;
    }

    public Double getEntropy() {
        return entropy;
    }

    public void setEntropy(Double entropy) {
        this.entropy = entropy;
    }

    public Map<String, Double> getCharacterProbabilities() {
        return characterProbabilities;
    }

    public void setCharacterProbabilities(Map<String, Double> characterProbabilities) {
        this.characterProbabilities = characterProbabilities;
    }

    public Long getCharSum() {
        return charSum;
    }

    public void setCharSum(Long charSum) {
        this.charSum = charSum;
    }
}
