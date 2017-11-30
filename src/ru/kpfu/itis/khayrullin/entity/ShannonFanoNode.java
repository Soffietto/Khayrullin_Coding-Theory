package ru.kpfu.itis.khayrullin.entity;

public class ShannonFanoNode {
    private String symbol;
    private Long count;
    private String code;

    public ShannonFanoNode(String symbol, Long count) {
        this.symbol = symbol;
        this.count = count;
        this.code = "";
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ShannonFanoNode{" +
                "symbol='" + symbol + '\'' +
                ", count=" + count +
                ", code='" + code + '\'' +
                '}';
    }
}
