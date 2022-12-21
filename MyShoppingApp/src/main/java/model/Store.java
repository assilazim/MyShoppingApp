package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {
    private String storeCode;
    private String name;
    private String streetName;
    private String streetNo;
    private String postalCode;
    private String city;
    private String country;
    private boolean isAvailable;
    private String openingHours;
    private ArrayList<Item> itemArrayList;

    public Store(String storeCode, String name, String streetName, String streetNo, String postalCode, String city,
                 String country, boolean isAvailable, String openingHours) {
        this.storeCode = storeCode;
        this.name = name;
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.isAvailable = isAvailable;
        this.openingHours = openingHours;
        this.itemArrayList = new ArrayList<>();
    }

    public String getStoreCode() {
        return storeCode;
    }

    public String getStreetName() {
        return streetName;
    }


    public String getStreetNo() {
        return streetNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public boolean addItem(Item itemToAdd){
        Item existingItem = itemArrayList.stream().filter(item -> item.getName().equalsIgnoreCase(itemToAdd.getName())).findFirst().orElse(null);
        if (existingItem != null) {
            itemArrayList.remove(existingItem);
        }
        itemArrayList.add(itemToAdd);
        return true;
    }

    public boolean removeItem(int itemIndex){
        if(itemIndex < itemArrayList.size()){
            itemArrayList.remove(itemIndex);
        }
        return true;
    }

    @Override
    public String toString() {
        return name + " | " +
                streetName + " | "+
                streetNo + " | " +
                postalCode + " | " +
                city + " | " +
                country + " | " +
                openingHours;
    }
}
