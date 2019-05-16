package coder.models;

public class DishPrint {

    private String name;
    private int price,qty,total;

    public DishPrint(String name, int price, int qty, int total) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
