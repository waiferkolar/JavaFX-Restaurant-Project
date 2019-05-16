package coder.models;

public class OrderDetail {

    private int id,price,count,total;
    private String name;

    public OrderDetail(int id, int price, int count, int total, String name) {
        this.id = id;
        this.price = price;
        this.count = count;
        this.total = total;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", price=" + price +
                ", count=" + count +
                ", total=" + total +
                ", name='" + name + '\'' +
                '}';
    }
}
