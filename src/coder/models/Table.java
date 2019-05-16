package coder.models;

public class Table {
    private int id, charge, chairs;
    private boolean enabled;

    public Table(int id, int charge, int chairs, boolean enabled) {
        this.id = id;
        this.charge = charge;
        this.chairs = chairs;
        this.enabled = enabled;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public boolean isEnable() {
        return enabled;
    }

    public void setEnable(boolean enable) {
        this.enabled = enable;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", charge=" + charge +
                ", chairs=" + chairs +
                ", enable=" + enabled +
                '}';
    }
}
