package coder.models;

public class SaleHsitory {
    private String saleDate;
    private int saleTotal;


    public SaleHsitory(String saleDate, int saleTotal) {
        this.saleDate = saleDate;
        this.saleTotal = saleTotal;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public int getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(int saleTotal) {
        this.saleTotal = saleTotal;
    }
}
