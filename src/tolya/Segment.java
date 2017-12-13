package tolya;

public class Segment {
    private String symbol;
    private double leftBorder;
    private double rightBorder;

    public Segment(String symbol, double leftBorder, double rightBorder) {
        this.symbol = symbol;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(double leftBorder) {
        this.leftBorder = leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(double rightBorder) {
        this.rightBorder = rightBorder;
    }
}