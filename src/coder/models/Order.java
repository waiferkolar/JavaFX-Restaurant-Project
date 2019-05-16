package coder.models;

public class Order {
    private int id, table_id, dish_id, price, count;
    private boolean enabled;

    public Order(int id, int table_id, int dish_id, int price, int count, boolean enabled) {
        this.id = id;
        this.table_id = table_id;
        this.dish_id = dish_id;
        this.price = price;
        this.count = count;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", table_id=" + table_id +
                ", dish_id=" + dish_id +
                ", price=" + price +
                ", count=" + count +
                ", enabled=" + enabled +
                '}';
    }
}
