package model;

import java.io.Serializable;

public class PurchasedItem implements Serializable {
    private String name;
    private double price;
    private int quantity;

    public PurchasedItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name  + " | "+
                "Quantity: "+quantity;
    }
}
