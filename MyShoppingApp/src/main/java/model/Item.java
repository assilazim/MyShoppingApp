package model;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private double price;
    private int quantity;
    private String aisle;
    private String category;
    private boolean onSale;

    public Item(String name, double price, int quantity, String aisle, String category, boolean onSale) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.aisle = aisle;
        this.category = category;
        this.onSale = onSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }



    @Override
    public String toString() {
        return name + " | "+
                "Price: "+price + " | "+
                "Available Quantity: "+quantity+ " | "+
                "Category: "+category+ " | "+
                "On Sale: "+ (onSale ? "Yes" : "No")+ " | "+
                "Aisle: "+aisle ;
    }
}
