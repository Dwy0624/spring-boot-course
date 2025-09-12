package top.dwy.enums;

public enum DrinkType {
    COFFEE("咖啡",10),
    TEA("奶茶",12),
    JUICE("果汁",10);
    private final String label;
    private final double price;

    DrinkType(String label, double price) {
        this.label = label;
        this.price = price;
    }
    public String getLabel() {
        return label;
    }
    public double getPrice() {
        return price;
    }

}
